package intercept.proxy;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HTTPParserTests {
    @Test
    public void canConstructParser() {
        new HTTPParser();
    }

    @Test
    @Ignore
    public void parserRecognisesHeaderSyntax() {
        String httpData = "GET /some/path HTTP/1.1\r\n\r\n";

        HTTPParser parser = new HTTPParser();

        parser.parse(httpData.getBytes());
        final String[] request = new String[3];
        parser.set(new HTTPParser.RequestHandler() {
            @Override
            public void onRequest(String method, String path, String protocol) {
                request[0] = method;
                request[1] = path;
                request[2] = protocol;
            }
        });

        assertThat(request[0], is("GET"));
        assertThat(request[1], is("/some/path"));
        assertThat(request[2], is("HTTP/1.1"));
    }
}
