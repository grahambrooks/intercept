package intercept.utils;

import intercept.model.UriMatcher;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UriMatchersUnitTests {
    @Test
    public void simpleMatcherToStringIsTheMatcherText(){
        UriMatcher uriMatcher = UriMatchers.simpleMatcher("hello");

        assertThat(uriMatcher.toString(), is("hello"));
    }
}
