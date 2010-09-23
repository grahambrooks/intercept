package intercept.configuration;

import intercept.framework.UriMatcher;
import intercept.server.UriMatchers;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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

    @Test
    public void simpleMatcherMatchesOnlyThePath() throws URISyntaxException {
        UriMatcher matcher = UriMatchers.simpleMatcher("/");

        assertThat(matcher.matches(new URI("http://foobar:8080/")), is(true));
    }
}
