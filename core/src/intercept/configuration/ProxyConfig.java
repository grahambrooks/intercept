package intercept.configuration;

import intercept.model.Route;
import intercept.model.UriMatcher;
import intercept.proxy.HTTPRequest;

import java.util.List;
import java.util.Map;

public interface ProxyConfig {
    void setPort(int port);

    public int getPort();

    void setName(String name);

    public String getName();

    public void createOrUpdateStub(StubRequest stubRequest);

    public boolean stubbedRequest(String hostName);

    public void add(StubResponse stubResponse);

    public String getStubbedResponse(String hostName);

    public Map<UriMatcher, StubResponse> getStubs();

    public String getOutgoingProxyHost();

    public void setOutgoingProxy(String outgoingProxy);

    public int getOutgoingProxyPort();

    public boolean hasOutgoingProxyFor(String hostname);

    public List<Route> getRoutes();

    public void addRoute(String domainPattern, String targetAddress);

    public void transformRoute(final HTTPRequest request);

    public void setLogLevel(int logLevel);

    public int getLogLevel();

}
