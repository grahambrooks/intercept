package intercept.configuration;

import intercept.model.UriComparator;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class Stubs {
    private Map<UriComparator, StubResponse> stubs;

    public Stubs() {
        this.stubs = new HashMap<UriComparator, StubResponse>();
    }

    public void createOrUpdateStub(StubRequest stubRequest) {
        stubRequest.define(stubs);
    }

    public boolean isStubbed(String hostName) {
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

    public StubResponse getStubbedResponse(String hostName) {
        for (UriComparator matcher : stubs.keySet()) {
            try {
                if (matcher.matches(new URI(hostName))) {
                    return stubs.get(matcher);
                }
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        return new StubResponse();
    }

    public void add(UriComparator uri, StubResponse stubResponse) {
        stubs.put(uri, stubResponse);
    }
}
