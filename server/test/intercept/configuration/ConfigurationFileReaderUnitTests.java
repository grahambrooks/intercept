package intercept.configuration;

import intercept.logging.ApplicationLog;
import intercept.logging.ConsoleApplicationLog;
import intercept.model.UriMatcher;
import intercept.utils.Block;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import static org.mockito.Mockito.mock;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;

public class ConfigurationFileReaderUnitTests {
    @Test
    public void canConfigureMiddlemanPort() {
        InputStream i = new ByteArrayInputStream("port = 10".getBytes());

        ConfigurationFileReader reader = new ConfigurationFileReader(mock(ApplicationLog.class));
        InterceptConfiguration config = new InterceptConfiguration(mock(ConsoleApplicationLog.class));
        reader.readConfiguration(i, config);

        assertThat(config.getConfigurationPort(), equalTo(10));
    }

    @Test
    public void readerAcceptsRouteForProxy() {
        String basicProxyDefn = "proxy foo { " +
                "}";
        InputStream i = new ByteArrayInputStream(basicProxyDefn.getBytes());

        ConfigurationFileReader reader = new ConfigurationFileReader(mock(ApplicationLog.class));
        InterceptConfiguration config = new InterceptConfiguration(mock(ConsoleApplicationLog.class));
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
        InterceptConfiguration config = new InterceptConfiguration(mock(ConsoleApplicationLog.class));
        reader.readConfiguration(i, config);

        assertThat(config.hasProxy("foo"), is(true));
    }

    @Test
    public void readerParsesStubDefinitions() {
        String basicProxyDefn = "proxy foo { " +
                "   stub foo.com {\n" +
                "      response = 200\n" +
                "      header = [hey-dude]\n" +
                "      body = [X]\n" +
                "   }\n" +
                "}";
        InputStream i = new ByteArrayInputStream(basicProxyDefn.getBytes());

        ConfigurationFileReader reader = new ConfigurationFileReader(mock(ApplicationLog.class));
        InterceptConfiguration config = new InterceptConfiguration(mock(ConsoleApplicationLog.class));
        reader.readConfiguration(i, config);

        assertThat(config.hasProxy("foo"), is(true));

        final StubResponse[] found = new StubResponse[]{null};
        Block<ProxyConfig> visitor = new Block<ProxyConfig>() {
            public void yield(ProxyConfig item) {
                for (UriMatcher matcher : item.getStubs().keySet()) {
                    if (matcher.matches(URI.create("foo.com"))) {
                        found[0] = item.getStubs().get(matcher);
                    }
                }
            }
        };

        config.eachProxy(visitor);

        assertThat(found[0].getBody(), is("X"));
        assertThat(found[0].getResponse(), is("hey-dude"));
    }
}
