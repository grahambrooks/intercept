package intercept.configuration;

import intercept.model.Route;
import intercept.model.UriMatcher;
import intercept.proxy.HTTPRequest;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProxyConfig {
    public static final int DEFAULT_PORT = 8080;
    private int port = DEFAULT_PORT;
    private String name;
    private List<Route> routes;
    private Map<UriMatcher, StubResponse> stubs;
    private URI outgoingProxy;
    private int debugLevel;

    public ProxyConfig() {
        this.name = "undefined";
        this.routes = new ArrayList<Route>();
        this.stubs = new HashMap<UriMatcher, StubResponse>();
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void createOrUpdateStub(StubRequest stubRequest) {
        stubRequest.define(stubs);
    }

    public String getOutgoingProxyHost() {
        if (outgoingProxy == null) {
            return "";
        }
        return outgoingProxy.getHost();
    }

    public int getOutgoingProxyPort() {
        if (outgoingProxy == null) {
            return 80;
        }
        return outgoingProxy.getPort();
    }

    public void addRoute(String domainPattern, String targetAddress) {
        routes.add(new Route(domainPattern, targetAddress));
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

    public boolean hasOutgoingProxyFor(String hostname) {
        if (outgoingProxy != null) {
            java.net.InetAddress localMachine = null;
            try {
                localMachine = java.net.InetAddress.getLocalHost();
                if (hostname.equalsIgnoreCase(localMachine.getHostName()) ||
                    hostname.equalsIgnoreCase(localMachine.getHostAddress()) ||
                    hostname.equalsIgnoreCase("0.0.0.0") ||
                    hostname.equalsIgnoreCase("127.0.0.1")) {
                    return false;
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

            return true;
        }
        return false;
    }

    public int getDebugLevel() {
        return debugLevel;
    }

    public boolean stubbedRequest(String hostName) {
        for (UriMatcher matcher : stubs.keySet()) {
            try {
                if (matcher.matches(new URI(hostName))) {
                    return true;
                }
            } catch (URISyntaxException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return false;  //To change body of created methods use File | Settings | File Templates.
    }

    public String getStubbedResponse(String hostName) {
        for (UriMatcher matcher : stubs.keySet()) {
            try {
                if (matcher.matches(new URI(hostName))) {
                    return stubs.get(matcher).getResponse();
                }
            } catch (URISyntaxException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return "";  //To change body of created methods use File | Settings | File Templates.
    }

    public Map<UriMatcher, StubResponse> getStubs() {
        return stubs;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoute(String text) {
    }

    public void setOutgoingProxy(String outgoingProxy) {
        this.outgoingProxy = URI.create(outgoingProxy.replaceAll("\"", ""));
    }

    public void setDebugLevel(int debugLevel) {
        this.debugLevel = debugLevel;
    }

    public void add(StubResponse stubResponse) {
        stubs.put(stubResponse.getPath(), stubResponse);
    }
}
