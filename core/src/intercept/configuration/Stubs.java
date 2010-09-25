package intercept.configuration;

import intercept.model.UriComparator;

import java.util.HashMap;
import java.util.Map;

import static intercept.utils.Utils.uri;

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
            if (matcher.matches(uri(hostName))) {
                return true;
            }
        }
        return false;
    }

    public StubResponse getStubbedResponse(String hostName) {
        for (UriComparator matcher : stubs.keySet()) {
            if (matcher.matches(uri(hostName))) {
                return stubs.get(matcher);
            }
        }
        return new StubResponse();
    }

    public void add(UriComparator uri, StubResponse stubResponse) {
        stubs.put(uri, stubResponse);
    }
}
