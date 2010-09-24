package intercept.configuration;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DefaultProxyConfigUnitTests {
    @Test
    public void proxyNameAndPortRequiredForConstruction() {
        DefaultProxyConfig config = new DefaultProxyConfig("the name", 20);

        assertThat(config.getName(), is("the name"));
        assertThat(config.getPort(), is(21));
    }
}
