package intercept.configuration;

import intercept.model.UriMatcher;
import intercept.server.UriMatchers;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

public class UriMatcherUnitTests {
    @Test
    public void simpleMatcherMatchesSimpleRoot() throws URISyntaxException {
        UriMatcher matcher = UriMatchers.simpleMatcher("/");

        assertThat(matcher.matches(new URI("/")), is(true));
        assertThat(matcher.matches(new URI("/home")), is(false));
    }

    @Test
    public void simpleMatcherMatchesSimplePaths() throws URISyntaxException {
        UriMatcher matcher = UriMatchers.simpleMatcher("/proxy/new");

        assertThat(matcher.matches(new URI("/proxy/new")), is(true));
    }
}
