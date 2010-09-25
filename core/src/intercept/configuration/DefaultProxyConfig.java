package intercept.configuration;

import intercept.proxy.HTTPRequest;

import java.net.URI;
import java.net.UnknownHostException;

public class DefaultProxyConfig implements ProxyConfig {
    private int port = DEFAULT_PORT_NO;
    private String name;
    private HostOverrides overrides;
    private Stubs stubs;
    private URI outgoingProxy;
    private int logLevel;
    private static final int DEFAULT_PORT_NO = 8080;
    private static final int HTTP_PORT = 80;

    public DefaultProxyConfig(String name, int port) {
        this.name = name;
        this.port = port;
        this.stubs = new Stubs();
        this.overrides = new HostOverrides();
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
        stubs.createOrUpdateStub(stubRequest);
    }

    public String getOutgoingProxyHost() {
        if (outgoingProxy == null) {
            return "";
        }
        return outgoingProxy.getHost();
    }

    public int getOutgoingProxyPort() {
        if (outgoingProxy == null) {
            return HTTP_PORT;
        }
        return outgoingProxy.getPort();
    }

    public void addRoute(String domainPattern, String targetAddress) {
        overrides.define(domainPattern, targetAddress);
    }

    public void transformRoute(final HTTPRequest request) {
        overrides.transformRoute(request);
    }

    public boolean hasOutgoingProxyFor(String hostname) {
        if (outgoingProxy != null) {
            java.net.InetAddress localMachine;
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
        return stubs.isStubbed(hostName);
    }

    public StubResponse getStubbedResponse(String hostName) {
        return stubs.getStubbedResponse(hostName);
    }

    public Stubs getStubs() {
        return stubs;
    }

    public HostOverrides getOverrides() {
        return overrides;
    }

    public void setOutgoingProxy(String outgoingProxy) {
        this.outgoingProxy = URI.create(outgoingProxy.replaceAll("\"", ""));
    }

    public void setLogLevel(int logLevel) {
        this.logLevel = logLevel;
    }

    public void add(StubResponse stubResponse) {
        stubs.add(stubResponse.getUri(), stubResponse);
    }

}
