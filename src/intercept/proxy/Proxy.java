package intercept.proxy;

import intercept.configuration.ProxyConfig;
import intercept.logging.ApplicationLog;

import java.util.ArrayList;
import java.util.List;

public class Proxy {
    private static List<ProxyServer> proxies = new ArrayList<ProxyServer>();

    public static ProxyServer startProxy(ProxyConfig proxyConfig, ApplicationLog applicationLog) {
        ProxyServer proxyServer = new ProxyServer(proxyConfig, applicationLog);
        proxyServer.start();

        proxies.add(proxyServer);
        return proxyServer;
    }

    public static void stopProxy(ProxyConfig proxyConfig) {
        for (ProxyServer proxy : proxies) {
            if (proxyConfig.getName().equals(proxy.getName())) {
                proxy.stop();
            }
        }
    }

}
