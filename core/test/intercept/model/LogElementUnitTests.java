package intercept.model;

import intercept.model.LogElement;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;

public class LogElementUnitTests {
    @Test
    public void logElementPreservesMessage() {
        LogElement logElement = new LogElement("a message");

        assertThat(logElement.getMessage(), is("a message"));
    }
}
