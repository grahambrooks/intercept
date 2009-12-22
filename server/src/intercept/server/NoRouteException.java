package intercept.server;

import java.net.URI;

public class NoRouteException extends RuntimeException {
    public NoRouteException(URI url) {
        super("No route available for " + url);
    }
}
