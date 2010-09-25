package intercept.proxy;

import intercept.configuration.DefaultProxyConfig;
import intercept.configuration.ProxyConfig;
import intercept.configuration.StubResponse;
import intercept.logging.ApplicationLog;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class InterceptProxyServerUnitTests {
    ApplicationLog log = mock(ApplicationLog.class);

    @Test
    public void serverStartsAndStops() {
        ProxyConfig config = new DefaultProxyConfig();
        config.setName("test");
        config.setPort(2001);


        InterceptProxyServer server = new InterceptProxyServer(config, log);

        server.start();
        server.stop();
    }

    @Test
    public void proxyHandlesStubbing() throws IOException {
        ProxyConfig config = new DefaultProxyConfig();

        config.setName("test");
        config.setPort(2000);

        StubResponse response = new StubResponse();
        response.setUrl("http://noname.com/a");
        response.setBody("Hello");
        response.setHeader("GET /");
        config.add(response);

        InterceptProxyServer server = new InterceptProxyServer(config, log);

        server.start();

        HttpClient client = new HttpClient();

        GetMethod method = new GetMethod("http://noname.com/a");
        HostConfiguration hostConfig = new HostConfiguration();
        hostConfig.setProxy("0.0.0.0", 2000);
        client.executeMethod(hostConfig, method);

        server.stop();

        String body = new String(method.getResponseBody());
        assertThat(body, is("Hello"));
    }
}
