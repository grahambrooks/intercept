package intercept.configuration;

import intercept.proxy.HTTPRequest;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HostOverridesUnitTests {
    @Test
    public void canDefineAHostnameReplacement() {
        HostOverrides overrides = new HostOverrides();

        overrides.define("google.com", "yahoo.com");
    }

    @Test
    public void transformsToTargetRoute() {
        HostOverrides overrides = new HostOverrides();

        overrides.define("google.com", "yahoo.com");

        HTTPRequest request = mock(HTTPRequest.class);

        when(request.hostName()).thenReturn("google.com");

        overrides.transformRoute(request);

        verify(request).replaceHost("yahoo.com");
    }

    @Test
    public void transformsToMatchingRoute() {
        HostOverrides overrides = new HostOverrides();

        overrides.define("google.com", "yahoo.com");
        overrides.define("thoughtworks.com", "agile.com");

        HTTPRequest request = mock(HTTPRequest.class);

        when(request.hostName()).thenReturn("thoughtworks.com");

        overrides.transformRoute(request);

        verify(request).replaceHost("agile.com");
    }
}
