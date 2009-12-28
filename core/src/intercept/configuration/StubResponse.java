package intercept.configuration;

import intercept.model.UriMatcher;
import static intercept.utils.UriMatchers.simpleMatcher;

public class StubResponse {
    private UriMatcher path;
    private String response;
    private String body;

    public StubResponse(){

    }

    public StubResponse(UriMatcher path, String response) {
        this.path = path;
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public UriMatcher getPath() {
        return path;
    }

    public void setUrl(String text) {
        path = simpleMatcher(text);
    }

    public void setResponseCode(String responseCode) {
    }

    public void setHeader(String response) {
        response = response.substring(1, response.length()-1);
        this.response = response;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }
}
