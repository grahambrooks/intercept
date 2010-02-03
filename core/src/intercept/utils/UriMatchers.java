package intercept.utils;

import intercept.model.UriMatcher;

import java.net.URI;

public class UriMatchers {
    public static UriMatcher simpleMatcher(final String name) {
        return new UriMatcher() {
            public boolean matches(URI requestUri) {
                return name.compareToIgnoreCase(requestUri.toString()) == 0;
            }

            public String toString() {
                return name;
            }
        };
    }
}
