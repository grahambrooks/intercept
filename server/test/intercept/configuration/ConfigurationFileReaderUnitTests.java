package intercept.configuration;

import intercept.logging.ApplicationLog;
import intercept.logging.ConsoleApplicationLog;
import intercept.utils.Block;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class ConfigurationFileReaderUnitTests {
    @Test
    public void canConfigureMiddlemanPort() {
        InputStream i = new ByteArrayInputStream("port = 10".getBytes());

        ConfigurationFileReader reader = new ConfigurationFileReader(mock(ApplicationLog.class));
        InterceptConfiguration config = new DefaultInterceptConfiguration(mock(ConsoleApplicationLog.class));
        reader.readConfiguration(i, config);

        assertThat(config.getConfigurationPort(), equalTo(10));
    }

    @Test
    public void readerAcceptsRouteForProxy() {
        String basicProxyDefn = "proxy foo { " +
                "}";
        InputStream i = new ByteArrayInputStream(basicProxyDefn.getBytes());

        ConfigurationFileReader reader = new ConfigurationFileReader(mock(ApplicationLog.class));
        InterceptConfiguration config = new DefaultInterceptConfiguration(mock(ConsoleApplicationLog.class));
        reader.readConfiguration(i, config);

        assertThat(config.hasProxy("foo"), is(true));
    }

    @Test
    public void readerParsesProxyAttributes() {
        String basicProxyDefn = "proxy foo {\n" +
                "port = 12\n" +
                "route \"www.from.com\" => \"www.to.com\"\n" +
                "outgoing-proxy = \"http://www.proxy.com:8080\"\n" +
                "debug = 1\n" +
                "}";
        InputStream i = new ByteArrayInputStream(basicProxyDefn.getBytes());

        ConfigurationFileReader reader = new ConfigurationFileReader(mock(ApplicationLog.class));
        InterceptConfiguration config = new DefaultInterceptConfiguration(mock(ConsoleApplicationLog.class));
        reader.readConfiguration(i, config);

        assertThat(config.hasProxy("foo"), is(true));
    }

    @Test
    public void readerParsesStubDefinitions() {
        String basicProxyDefn = "proxy foo { " +
                "   stub foo.com {\n" +
                "      response = 200\n" +
                "      header = [hey-dude]\n" +
                "      header = [check it out]\n" +
                "      body = [X]\n" +
                "   }\n" +
                "}";
        InputStream i = new ByteArrayInputStream(basicProxyDefn.getBytes());

        ConfigurationFileReader reader = new ConfigurationFileReader(mock(ApplicationLog.class));
        InterceptConfiguration config = new DefaultInterceptConfiguration(mock(ConsoleApplicationLog.class));
        reader.readConfiguration(i, config);

        assertThat(config.hasProxy("foo"), is(true));


        final StubResponse[] found = new StubResponse[]{null};
        Block<ProxyConfig> visitor = new Block<ProxyConfig>() {
            public void yield(ProxyConfig item) {
                if (item.stubbedRequest("foo.com")) {
                    found[0] = item.getStubbedResponse("foo.com");
                }
            }
        };

        config.eachProxy(visitor);

        assertThat(found[0].getResponseCode(), is(200));
        assertThat(found[0].getBody(), is("X"));
        assertThat(found[0].getHeaders(), is("hey-dude\r\ncheck it out"));
    }
}
