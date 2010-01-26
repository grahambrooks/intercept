package intercept.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ResponseDataLogElementUnitTests {
    @Test
    public void messageContainsRequestAndResponseText() {
        ResponseDataLogElement element = new ResponseDataLogElement("abc".getBytes(), "xyz".getBytes());

        assertThat(element.getMessage(), is("REQUEST:\nabc\nRESPONSE:\nxyz"));
    }
}
