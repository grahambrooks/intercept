package intercept.model;

import java.net.URI;

public interface UriMatcher {
    boolean matches(URI requestUri);
}
