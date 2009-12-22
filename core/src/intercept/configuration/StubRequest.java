package intercept.configuration;

import intercept.model.UriMatcher;
import static intercept.utils.UriMatchers.simpleMatcher;

import java.util.Map;

public class StubRequest {
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
