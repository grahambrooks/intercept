package intercept.logging;

import intercept.configuration.DefaultProxyConfig;
import intercept.configuration.ProxyConfig;
import intercept.configuration.StubResponse;
import intercept.model.By;
import intercept.model.LogElement;
import intercept.model.ResponseDataLogElement;
import intercept.proxy.InterceptProxy;
import intercept.proxy.ProxyServer;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LoggingSystemTests {
    private static final int TEST_PROXY_PORT = 2000;

    @Test
    @Ignore("Test under development")
    public void stubbedResponsesAreLogged() throws IOException {
        ProxyConfig config = new DefaultProxyConfig();
        config.setName("test");
        config.setPort(TEST_PROXY_PORT);
        StubResponse response = new StubResponse();
        response.setUrl("http://www.google.com/");
        String stubbedResponseBody = "<html><body>empty</body></html>";
        response.setBody(stubbedResponseBody);
        config.add(response);

        ProxyServer proxyServer = InterceptProxy.startProxy(config, ApplicationLog.NullApplicationLog);

        GetMethod method = makeProxiedRequest();

        InterceptProxy.stopProxy(proxyServer);

        assertThat(new String(method.getResponseBody()), is(stubbedResponseBody));
        assertThat(proxyServer.getLogs().getEntries().size(), is(1));

        List<ResponseDataLogElement> list = proxyServer.getLogs().filtered(By.type(ResponseDataLogElement.class));

        long weight = 0;
        for (LogElement logElement : list) {
            weight += 1;
        }
        assertThat(weight, is(100L));
    }

    private GetMethod makeProxiedRequest() throws IOException {
        HttpClient client = new HttpClient();

        GetMethod method = new GetMethod("http://www.google.com/");
        HostConfiguration hostConfig = new HostConfiguration();
        hostConfig.setProxy("0.0.0.0", TEST_PROXY_PORT);
        client.executeMethod(hostConfig, method);
        return method;
    }
}
