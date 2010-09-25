package intercept.configuration;

import intercept.model.Route;
import intercept.proxy.HTTPRequest;

import java.util.ArrayList;
import java.util.List;

public class HostOverrides {
    private List<Route> routes;

    public HostOverrides() {
        routes = new ArrayList<Route>();
    }

    public void define(final String pattern, final String target) {
        routes.add(new Route(pattern, target));
    }

    public void transformRoute(final HTTPRequest request) {
        String hostname = request.hostName();
        for (Route route : routes) {
            if (route.matches(hostname)) {
                request.replaceHost(route.getTargetAddress());
                return;
            }
        }
    }

    public List<Route> getRoutes() {
        return routes;
    }
}
