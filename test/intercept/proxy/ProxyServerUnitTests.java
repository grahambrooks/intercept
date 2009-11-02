package intercept.proxy;

import com.sun.net.httpserver.HttpServer;
import intercept.configuration.ProxyConfig;
import org.junit.Test;
import static org.mockito.Mockito.mock;

public class ProxyServerUnitTests {
    @Test
    public void proxyServerCreatesContextOnGivenServer() {
        ProxyConfig config = new ProxyConfig();
        config.setName("foo");

        ProxyServer proxy = new ProxyServer(config, null);
        HttpServer server = mock(HttpServer.class);
    }
}
