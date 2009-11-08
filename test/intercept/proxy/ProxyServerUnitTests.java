package intercept.proxy;

import intercept.configuration.ProxyConfig;
import org.junit.Test;
import static org.mockito.Mockito.mock;

public class ProxyServerUnitTests {
    @Test
    public void proxyServerCreatesContextOnGivenServer() {
        ProxyConfig config = new ProxyConfig();
        config.setName("foo");

        ProxyServer proxy = new ProxyServer(config, null);
    }
}
