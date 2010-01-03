package intercept.model;

import intercept.proxy.HTTPRequest;

public class RequestLogElement extends LogElement {
    private String address;
    private int port;
    private HTTPRequest request;

    public RequestLogElement(String address, int port, HTTPRequest request) {
        super();

        this.address = address;
        this.port = port;
        this.request = request;
    }

    @Override
    public String getMessage() {
        return "Request from " + address + ":" + port
                + " -> "
                + request.hostName() + ":" + request.hostPortNumber()
                + request.getPath()
                + " of " + request.length() + "&nbsp;bytes";
    }
}
