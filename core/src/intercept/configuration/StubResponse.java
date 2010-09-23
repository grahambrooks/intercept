package intercept.configuration;

import intercept.model.UriComparator;

import java.net.URI;
import java.net.URISyntaxException;

import static intercept.utils.UriComparators.fullComparator;

public class StubResponse {
    private UriComparator uri;
    private String response;
    private String body;

    public StubResponse() {

    }

    public StubResponse(UriComparator uri, String response) {
        this.uri = uri;
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public UriComparator getUri() {
        return uri;
    }

    public void setUrl(String uri) {
        try {
            this.uri = fullComparator(new URI(uri));
        } catch (URISyntaxException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void setResponseCode(String responseCode) {
    }

    public void setHeader(String response) {
        response = response.substring(1, response.length() - 1);
        this.response = response;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }
}
