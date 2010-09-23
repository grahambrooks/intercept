package intercept.utils;

import intercept.model.UriComparator;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UriComparatorUnitTests {

    @Test
    public void comparatorTestsHost() throws URISyntaxException {
        UriComparator matcher = UriComparators.fullComparator(new URI("http://www.foo.com/"));

        assertThat(matcher.matches(new URI("/")), is(false));
        assertThat(matcher.matches(new URI("http://www.foo.com/")), is(true));
    }

    @Test
    public void comparatorMatchesPathIfHostMissing() throws URISyntaxException {
        UriComparator matcher = UriComparators.fullComparator(new URI("/home"));

        assertThat(matcher.matches(new URI("/home")), is(true));
    }

    @Test
    public void simpleMatcherMatchesSimpleRoot() throws URISyntaxException {
        UriComparator matcher = UriComparators.fullComparator(new URI("/"));

        assertThat(matcher.matches(new URI("/")), is(true));
        assertThat(matcher.matches(new URI("/home")), is(false));
    }

    @Test
    public void simpleMatcherMatchesSimplePaths() throws URISyntaxException {
        UriComparator matcher = UriComparators.fullComparator(new URI("/proxy/new"));

        assertThat(matcher.matches(new URI("/proxy/new")), is(true));
    }
}
