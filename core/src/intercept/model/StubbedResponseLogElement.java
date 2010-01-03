package intercept.model;

import intercept.proxy.HTTPRequest;

public class StubbedResponseLogElement extends LogElement {
    private String address;
    private int port;
    private HTTPRequest request;
    private String response;

    public StubbedResponseLogElement(String address, int port, HTTPRequest request, String response) {
        this.address = address;
        this.port = port;
        this.request = request;
        this.response = response;
    }

    @Override
    public String getMessage() {
        return "Request from " + address + ":" + port
                + " STUBBED "
                + request.length() + " bytes sent "
                + response.length() + " bytes returned "
                + response;
    }
}
