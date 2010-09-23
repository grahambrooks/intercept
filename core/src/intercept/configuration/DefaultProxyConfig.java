package intercept.configuration;

import intercept.model.Route;
import intercept.model.UriComparator;
import intercept.proxy.HTTPRequest;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultProxyConfig implements ProxyConfig {
    private int port = DEFAULT_PORT_NO;
    private String name;
    private List<Route> routes;
    private Map<UriComparator, StubResponse> stubs;
    private URI outgoingProxy;
    private int logLevel;
    private static final int DEFAULT_PORT_NO = 8080;

    public DefaultProxyConfig(String name, int port) {
        this.name = name;
        this.port = port;
        this.routes = new ArrayList<Route>();
        this.stubs = new HashMap<UriComparator, StubResponse>();
    }

    public DefaultProxyConfig() {
        this("undefined", DEFAULT_PORT_NO);
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
                e.printStackTrace();
            }

            return true;
        }
        return false;
    }

    public int getLogLevel() {
        return logLevel;
    }

    public boolean stubbedRequest(String hostName) {
        for (UriComparator matcher : stubs.keySet()) {
            try {
                if (matcher.matches(new URI(hostName))) {
                    return true;
                }
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public String getStubbedResponse(String hostName) {
        for (UriComparator matcher : stubs.keySet()) {
            try {
                if (matcher.matches(new URI(hostName))) {
                    return stubs.get(matcher).getBody();
                }
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public Map<UriComparator, StubResponse> getStubs() {
        return stubs;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setOutgoingProxy(String outgoingProxy) {
        this.outgoingProxy = URI.create(outgoingProxy.replaceAll("\"", ""));
    }

    public void setLogLevel(int logLevel) {
        this.logLevel = logLevel;
    }

    public void add(StubResponse stubResponse) {
        stubs.put(stubResponse.getUri(), stubResponse);
    }

}
