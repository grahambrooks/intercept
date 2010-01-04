package intercept.proxy;

import intercept.configuration.DefaultProxyConfig;
import intercept.configuration.ProxyConfig;
import org.junit.Test;

public class ProxyServerUnitTests {
    @Test
    public void proxyServerCreatesContextOnGivenServer() {
        ProxyConfig config = new DefaultProxyConfig();
        config.setName("foo");

        new InterceptProxyServer(config, null);
    }
}
