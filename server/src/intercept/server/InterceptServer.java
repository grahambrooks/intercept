package intercept.server;

import intercept.configuration.InterceptConfiguration;

public interface InterceptServer {
    void start(InterceptConfiguration configuration);

    void stop(InterceptConfiguration configuration);

    String uri(String path);
}
