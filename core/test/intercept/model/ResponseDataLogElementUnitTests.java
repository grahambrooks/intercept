package intercept.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ResponseDataLogElementUnitTests {
    @Test
    public void canConstructWithNoData() {
        new ResponseDataLogElement(null, null);
    }

    @Test
    public void messageContainsRequestAndResponseText() {
        ResponseDataLogElement element = new ResponseDataLogElement("abc".getBytes(), "xyz".getBytes());

        assertThat(element.getMessage(), is("REQUEST:\nabc\nRESPONSE:\nxyz"));
    }

    @Test
    public void logElementDataIsImutable() {
        byte[] request = "abc".getBytes();
        byte[] response = "xyz".getBytes();

        ResponseDataLogElement element = new ResponseDataLogElement(request, response);

        response[1] = request[1] = 'g';


        assertThat(element.getMessage(), is("REQUEST:\nabc\nRESPONSE:\nxyz"));
    }

    @Test
    public void messageContainsNoDataForNull() {
        ResponseDataLogElement element = new ResponseDataLogElement(null, null);
        assertThat(element.getMessage(), is("REQUEST:\n\nRESPONSE:\n"));
    }
}
