package intercept.proxy;

import intercept.logging.EventLog;

public interface ProxyServer {
    void start();

    void stop();

    EventLog getLogs();

}
