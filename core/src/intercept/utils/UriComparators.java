package intercept.utils;

import intercept.model.UriComparator;

import java.net.URI;

public class UriComparators {
    public static UriComparator fullComparator(final URI uri) {
        return new UriComparator() {
            public boolean matches(URI testUri) {
                return compareStrings(uri.getHost(), testUri.getHost())
                        && uri.getPort() == testUri.getPort()
                        && compareStrings(uri.getPath(), testUri.getPath())
                        && compareStrings(uri.getScheme(), testUri.getScheme());
            }

            private boolean compareStrings(String s1, String s2) {
                return s1 == null && s2 == null || (s1 == null) == (s2 == null) && s1.compareToIgnoreCase(s2) == 0;
            }

            public String toString() {
                return uri.toString();
            }
        };
    }
}
