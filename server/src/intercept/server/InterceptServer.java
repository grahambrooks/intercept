package intercept.server;

import intercept.configuration.InterceptConfiguration;
import intercept.configuration.ProxyConfig;

import java.util.List;

public interface InterceptServer {
    void start(InterceptConfiguration configuration);

    ProxyConfig getConfig();

    List<ProxyConfig> getRunningProxies();

    void startNewProxy(String name, int port);

    void stop(InterceptConfiguration configuration);

    String uri(String path);
}
