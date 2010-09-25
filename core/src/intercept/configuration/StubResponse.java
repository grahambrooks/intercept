package intercept.configuration;

import intercept.model.UriComparator;
import intercept.utils.Utils;

import static intercept.utils.UriComparators.fullComparator;

public class StubResponse {
    private UriComparator uri;
    private String header;
    private String body;
    private int responseCode;

    public StubResponse() {
        this.header = "";
    }

    public StubResponse(UriComparator uri, String header, String body) {
        this.uri = uri;
        this.header = header;
        this.body = body;
    }

    public String getHeaders() {
        return header;
    }

    public UriComparator getUri() {
        return uri;
    }

    public void setUrl(String uriSpec) {
        this.uri = fullComparator(Utils.uri(uriSpec));
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = Integer.parseInt(responseCode);
    }

    public void setHeader(final String response) {
        if (this.header.length() > 0) {
            this.header += "\r\n";
        }
        this.header += response;
    }

    public void setBody(final String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public int getResponseCode() {
        return responseCode;
    }
}
