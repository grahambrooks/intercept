package intercept.proxy;

import intercept.configuration.ProxyConfig;
import intercept.logging.EventLog;
import intercept.utils.Function;

public interface ProxyServer {
    void start();
    ProxyStatus status();
    void stop();

    EventLog getLogs();

    String getName();

    ProxyConfig getConfig();

    <T> T response(Function<T, EventLog> function);
}
