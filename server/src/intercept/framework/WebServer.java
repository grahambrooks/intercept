package intercept.framework;

import intercept.configuration.ProxyConfig;

import java.util.List;

public interface WebServer {
    ProxyConfig getConfig();

    List<ProxyConfig> getRunningProxies();

    void startNewProxy(String name, int port);
}
