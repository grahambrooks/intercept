package intercept.utils;

import intercept.model.UriComparator;
import org.junit.Test;

import static intercept.utils.Utils.uri;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UriComparatorUnitTests {
    @Test
    public void simpleMatcherToStringIsTheMatcherText() {
        UriComparator uriMatcher = UriComparators.fullComparator(uri("hello"));

        assertThat(uriMatcher.toString(), is("hello"));
    }

    @Test
    public void comparatorTestsHost() {
        UriComparator matcher = UriComparators.fullComparator(uri("http://www.proxyNameAndPortRequiredForConstruction.com/"));

        assertThat(matcher.matches(uri("/")), is(false));
        assertThat(matcher.matches(uri("http://www.proxyNameAndPortRequiredForConstruction.com/")), is(true));
    }

    @Test
    public void comparatorTestsScheme() {
        UriComparator matcher = UriComparators.fullComparator(uri("https://www.proxyNameAndPortRequiredForConstruction.com/"));

        assertThat(matcher.matches(uri("http://www.proxyNameAndPortRequiredForConstruction.com/")), is(false));
    }

    @Test
    public void comparatorTestsPort() {
        UriComparator matcher = UriComparators.fullComparator(uri("http://www.proxyNameAndPortRequiredForConstruction.com/"));

        assertThat(matcher.matches(uri("http://www.proxyNameAndPortRequiredForConstruction.com:80/")), is(false));
    }


    @Test
    public void comparatorMatchesPathIfHostMissing() {
        UriComparator matcher = UriComparators.fullComparator(uri("/home"));

        assertThat(matcher.matches(uri("/home")), is(true));
    }

    @Test
    public void simpleMatcherMatchesSimpleRoot() {
        UriComparator matcher = UriComparators.fullComparator(uri("/"));

        assertThat(matcher.matches(uri("/")), is(true));
        assertThat(matcher.matches(uri("/home")), is(false));
    }

    @Test
    public void simpleMatcherMatchesSimplePaths() {
        UriComparator matcher = UriComparators.fullComparator(uri("/proxy/new"));

        assertThat(matcher.matches(uri("/proxy/new")), is(true));
    }
}
