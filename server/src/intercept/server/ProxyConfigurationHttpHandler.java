package intercept.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import intercept.configuration.ProxyConfig;
import intercept.framework.WebServer;
import intercept.logging.ApplicationLog;
import intercept.proxy.InterceptProxy;
import intercept.proxy.ProxyServer;
import static intercept.server.UriMatchers.pathMatcher;
import intercept.server.components.NewStubCommand;
import intercept.server.components.ProxyConfigurationPresenter;
import intercept.server.components.ProxyLogPresenter;
import intercept.server.components.ResetProxyLogCommand;
import intercept.utils.Block;

import java.util.ArrayList;
import java.util.List;

public class ProxyConfigurationHttpHandler implements HttpHandler {
    private ProxyServer proxyServer;
    private ApplicationLog applicationLog;

    public ProxyConfigurationHttpHandler(ProxyServer proxyServer, ApplicationLog applicationLog) {
        this.proxyServer = proxyServer;
        this.applicationLog = applicationLog;
    }

    public void handle(HttpExchange httpExchange) {
        try {
            String method = httpExchange.getRequestMethod();

            // This is created in the wrong place
            Dispatcher dispatcher = new Dispatcher();
            dispatcher.register(pathMatcher(proxyServer.getName()), new ProxyConfigurationPresenter());
            dispatcher.register(pathMatcher(proxyServer.getName(), "stub", "new"), new NewStubCommand());
            dispatcher.register(pathMatcher(proxyServer.getName(), "log"), new ProxyLogPresenter(proxyServer.getLogs()));
            dispatcher.register(pathMatcher(proxyServer.getName(), "log", "reset"), new ResetProxyLogCommand(proxyServer.getLogs()));

            WebServer webServer = new WebServer(){
                @Override
                public ProxyConfig getConfig() {
                    return proxyServer.getConfig();
                }

                @Override
                public List<ProxyConfig> getRunningProxies() {
                    final List<ProxyConfig> configs = new ArrayList<ProxyConfig>();
                    InterceptProxy.eachProxy(new Block<ProxyServer>(){
                        @Override
                        public void yield(ProxyServer item) {
                            ProxyConfig config = new ProxyConfig();
                            configs.add(item.getConfig());
                        }
                    });
                    return configs;
                }

                @Override
                public void startNewProxy(String name, int port) {
                    ProxyConfig config = new ProxyConfig();
                    config.setName(name);
                    config.setPort(port);
                    InterceptProxy.startProxy(config, applicationLog);
                }
            };

            if (method.equalsIgnoreCase("GET")) {
                dispatcher.dispatchGetRequest(new WebContext(webServer, httpExchange));
            }

            if (method.equalsIgnoreCase("POST")) {
                dispatcher.dispatchPostRequest(new WebContext(webServer, httpExchange));
            }

        } catch (Exception e) {
            System.err.println("Error executing request");
            e.printStackTrace();
        }
    }
}
