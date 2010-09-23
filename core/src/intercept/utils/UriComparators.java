package intercept.utils;

import intercept.model.UriComparator;

import java.net.URI;

public class UriComparators {
    public static UriComparator fullComparator(final URI uri) {
        return new UriComparator() {
            public boolean matches(URI testUri) {
                return sameHost(testUri)
                        && uri.getPort() == testUri.getPort()
                        && (uri.getPath() == testUri.getPath() || uri.getPath().compareToIgnoreCase(testUri.getPath()) == 0);
            }

            private boolean sameHost(URI testUri) {
                if (uri.getHost() == null && testUri.getHost() == null) {
                    return true;
                }

                if ((uri.getHost() == null) != (testUri.getHost() == null)) {
                    return false;
                }

                return uri.getHost().compareToIgnoreCase(testUri.getHost()) == 0;
            }

            public String toString() {
                return uri.toString();
            }
        };
    }
}
