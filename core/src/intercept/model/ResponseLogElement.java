package intercept.model;

import intercept.proxy.HTTPRequest;
import intercept.utils.EventTimer;

public class ResponseLogElement extends LogElement {
    private String hostAddress;
    private int localPort;
    private EventTimer timer;
    private int responseLength;
    private HTTPRequest request;

    public ResponseLogElement(String hostAddress, int localPort, EventTimer timer, int responseLength, HTTPRequest request) {
        this.hostAddress = hostAddress;
        this.localPort = localPort;
        this.timer = timer;
        this.responseLength = responseLength;
        this.request = request;
    }


    @Override
    public String getMessage() {
        return "Response from " + hostAddress + ":" + localPort +
                " to "
                + request.hostName() + ":" + request.hostPortNumber()
                + " " + request.length() + " bytes sent"
                + " " + responseLength + " bytes returned "
                + "in " + timer
                ;
    }
}
