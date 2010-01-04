package intercept.server.components;

import intercept.configuration.StubRequest;
import intercept.framework.RequestDocument;

public class StubRequestDocument implements RequestDocument {
    StubRequest request = new StubRequest();

    @Override
    public void set(String header, String values) {
        if (header.equalsIgnoreCase("path")) {
            request.setPath(values);
        }

        if (header.equalsIgnoreCase("response")) {
            request.setResponse(values);
        }
    }


    public StubRequest getRequest() {
        return request;
    }
}
