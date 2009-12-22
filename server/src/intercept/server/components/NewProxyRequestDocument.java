package intercept.server.components;

import intercept.framework.RequestDocument;

public class NewProxyRequestDocument implements RequestDocument {
    public String proxyName;
    public int proxyPort;

    public void set(String header, String values) {
        if (header.equals("proxy-name")){
            proxyName = values;
        }
        if (header.equals("proxy-port")) {
            proxyPort = Integer.parseInt(values);
        }
    }

    public int getProxyPort() {
        return proxyPort;
    }

    public String getProxyName() {
        return proxyName;
    }
}
