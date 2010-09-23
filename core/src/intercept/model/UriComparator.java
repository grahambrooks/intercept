package intercept.model;

import java.net.URI;

public interface UriComparator {
    boolean matches(URI requestUri);
}
