package intercept.configuration;

import intercept.model.UriMatcher;
import static intercept.utils.UriMatchers.simpleMatcher;

import java.util.Map;

public class StubRequest {
    String path;
    String response;

    public void define(Map<UriMatcher, StubResponse> stubs) {
        UriMatcher key = simpleMatcher(path);
        stubs.put(key, new StubResponse(key, response));
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
