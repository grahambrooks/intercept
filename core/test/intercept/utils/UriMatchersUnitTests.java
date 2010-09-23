package intercept.utils;

import intercept.model.UriComparator;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UriMatchersUnitTests {
    @Test
    public void simpleMatcherToStringIsTheMatcherText() throws URISyntaxException {
        UriComparator uriMatcher = UriComparators.fullComparator(new URI("hello"));

        assertThat(uriMatcher.toString(), is("hello"));
    }
}
