package intercept.configuration;

import intercept.proxy.HTTPRequest;

public interface ProxyConfig {
    void setPort(int port);

    public int getPort();

    void setName(String name);

    public String getName();

    public void createOrUpdateStub(StubRequest stubRequest);

    public boolean stubbedRequest(String hostName);

    public void add(StubResponse stubResponse);

    public StubResponse getStubbedResponse(String hostName);

    public Stubs getStubs();

    public String getOutgoingProxyHost();

    public void setOutgoingProxy(String outgoingProxy);

    public int getOutgoingProxyPort();

    public boolean hasOutgoingProxyFor(String hostname);

    public void addRoute(String domainPattern, String targetAddress);

    public void transformRoute(final HTTPRequest request);

    public void setLogLevel(int logLevel);

    public int getLogLevel();

    HostOverrides getOverrides();
}
