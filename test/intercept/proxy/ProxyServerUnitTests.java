package intercept.proxy;

import intercept.configuration.ProxyConfig;
import org.junit.Test;
import static org.mockito.Mockito.mock;

public class ProxyServerUnitTests {
    @Test
    public void proxyServerCreatesContextOnGivenServer() {
        ProxyConfig config = new ProxyConfig();
        config.setName("foo");

        InterceptProxyServer proxy = new InterceptProxyServer(config, null);
    }
}
