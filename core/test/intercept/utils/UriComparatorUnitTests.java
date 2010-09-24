package intercept.utils;

import intercept.model.UriComparator;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UriComparatorUnitTests {
    @Test
    public void simpleMatcherToStringIsTheMatcherText() throws URISyntaxException {
        UriComparator uriMatcher = UriComparators.fullComparator(new URI("hello"));

        assertThat(uriMatcher.toString(), is("hello"));
    }

    @Test
    public void comparatorTestsHost() throws URISyntaxException {
        UriComparator matcher = UriComparators.fullComparator(new URI("http://www.proxyNameAndPortRequiredForConstruction.com/"));

        assertThat(matcher.matches(new URI("/")), is(false));
        assertThat(matcher.matches(new URI("http://www.proxyNameAndPortRequiredForConstruction.com/")), is(true));
    }

    @Test
    public void comparatorTestsScheme() throws URISyntaxException {
        UriComparator matcher = UriComparators.fullComparator(new URI("https://www.proxyNameAndPortRequiredForConstruction.com/"));

        assertThat(matcher.matches(new URI("http://www.proxyNameAndPortRequiredForConstruction.com/")), is(false));
    }

    @Test
    public void comparatorTestsPort() throws URISyntaxException {
        UriComparator matcher = UriComparators.fullComparator(new URI("http://www.proxyNameAndPortRequiredForConstruction.com/"));

        assertThat(matcher.matches(new URI("http://www.proxyNameAndPortRequiredForConstruction.com:80/")), is(false));
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
