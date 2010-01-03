package intercept.model;

import intercept.proxy.HTTPRequest;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RequestLogElementUnitTests {
    @Test
    public void requestLogElementMessageContainsDetailsOfRequest() {

        HTTPRequest httpRequest = mock(HTTPRequest.class);
        when(httpRequest.getPath()).thenReturn("/");
        when(httpRequest.hostPortNumber()).thenReturn(80);
        when(httpRequest.hostName()).thenReturn("unknown");
        
        RequestLogElement logElement = new RequestLogElement("address", 8000, httpRequest);

        String message = logElement.getMessage();
        assertThat(message, is("Request from address:8000 -> unknown:80/ of 0&nbsp;bytes"));
    }
}
