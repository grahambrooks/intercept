package intercept.configuration;

import intercept.model.UriComparator;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import static intercept.utils.UriComparators.fullComparator;

public class StubRequest {
    String path;
    String response;

    public void define(Map<UriComparator, StubResponse> stubs) {
        UriComparator key = null;
        try {
            key = fullComparator(new URI(path));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
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
