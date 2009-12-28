package intercept.proxy;

import intercept.configuration.ProxyConfig;
import intercept.logging.EventLog;

public interface ProxyServer {
    void start();
    ProxyStatus status();
    void stop();

    EventLog getLogs();

    String getName();

    ProxyConfig getConfig();
}
