package intercept.server;


import intercept.model.UriMatcher;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.regex.Pattern;

public class UriMatchers {
    public static UriMatcher patternMatcher(String patternText) {
        final Pattern pattern = Pattern.compile(patternText);
        return new UriMatcher() {
            public boolean matches(URI requestUri) {
                return pattern.matcher(requestUri.toString()).matches();
            }

            public String toString() {
                return pattern.toString();
            }
        };
    }

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

    static UriMatcher pathMatcher(final String... pathElements) {
        StringBuilder path = new StringBuilder();
        for (String pathElement : pathElements) {
            path.append("/")
                    .append(pathElement);
        }

        final String comparison = path.toString();
        return new UriMatcher() {

            public boolean matches(URI requestUri) {
                return comparison.compareToIgnoreCase(requestUri.toString()) == 0;
            }

            public String toString() {
                return comparison;
            }
        };
    }

    static UriMatcher classpathMatcher() {
        return new UriMatcher() {
            public boolean matches(URI requestUri) {
                InputStream stream = null;
                try {
                    stream = DefaultInterceptServer.class.getResourceAsStream(requestUri.toString());
                    return stream != null;
                } finally {
                    if (stream != null) {
                        try {
                            stream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
    }
}
