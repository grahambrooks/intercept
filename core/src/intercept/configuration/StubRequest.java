package intercept.configuration;

import intercept.model.UriComparator;

import java.util.Map;

import static intercept.utils.UriComparators.fullComparator;
import static intercept.utils.Utils.uri;

public class StubRequest {
    String path;
    String response;
    private String body;

    public void define(Map<UriComparator, StubResponse> stubs) {
        UriComparator key = null;
        key = fullComparator(uri(path));
        stubs.put(key, new StubResponse(key, response, body));
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
