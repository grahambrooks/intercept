package intercept.proxy;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class HTTPRequestUnitTests {

    @Test
    public void defaultHostIsUnknown() {
        HTTPRequest request = new HTTPRequest();
        assertThat(request.hostName(), is("unknown"));
    }

    @Test
    public void parsesHostFromHeader() {
        HTTPRequest request = new HTTPRequest();

        request.updateHost("Host: wookie:8080");

        assertThat(request.hostName(), is("wookie"));
        assertThat(request.hostPortNumber(), is(8080));
    }

    @Test
    public void canReplaceHost() {
        HTTPRequest request = new HTTPRequest();

        request.updateHost("Host: wookie:8080");
        request.replaceHost("warlock:8081");

        assertThat(request.hostName(), is("warlock"));
        assertThat(request.hostPortNumber(), is(8081));
    }

    @Test
    public void hostReplacedInResponseData() {
        HTTPRequest request = new HTTPRequest();
        request.setRequestData("this is a wookie".getBytes());

        request.updateHost("Host: wookie:8080");
        request.replaceHost("warlock:8081");

        assertThat(request.hostName(), is("warlock"));
        assertThat(request.hostPortNumber(), is(8081));
        assertThat(new String(request.getRequestData()), is("this is a warlock:8081"));
    }

    @Test
    public void acceptsContentLengthHeader() {
        HTTPRequest request = new HTTPRequest();
        request.updateContentLength("Content-Length: 200");

        assertThat(request.contentLength, is(200));
    }

    @Test
    public void acceptsPathFromGetHeader(){
        HTTPRequest httpRequest = new HTTPRequest();

        httpRequest.updatePath("Get: /");

        assertThat(httpRequest.getPath(), is("/"));
    }

    @Test
    public void lengthIsTheNumberOfBytesOfHeaderData(){
        HTTPRequest request = new HTTPRequest();
        request.setRequestData("Some data".getBytes());
        assertThat(request.length(), is(9));
    }
}
