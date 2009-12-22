package intercept.server.components;

import intercept.configuration.StubResponse;
import intercept.framework.RequestDocument;
import intercept.model.UriMatcher;
import static intercept.server.UriMatchers.simpleMatcher;

import java.util.Map;

public class StubRequest implements RequestDocument {
    String path;
    String response;

    public void set(String header, String values) {
        if (header.equalsIgnoreCase("path")) {
            path = values;
        }

        if (header.equalsIgnoreCase("response")) {
            response = values;
        }
    }

    public void define(Map<UriMatcher, StubResponse> stubs) {
        UriMatcher key = simpleMatcher(path);
        stubs.put(key, new StubResponse(key, response));
    }

    public String getPath() {
        return path;
    }
}
