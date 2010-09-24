package intercept.proxy;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

public class HTTPParserTests {
    @Test
    public void canConstructParser() {
        new HTTPParser(null, null);
    }

    @Test
    @Ignore
    public void parserRecognisesHeaderSyntax() {
        String httpData = "GET /some/path HTTP/1.1\r\n\r\n";

        Automaton automaton = mock(Automaton.class);

        final String[] request = new String[3];
        HTTPParser parser = new HTTPParser(automaton, new HTTPParser.RequestHandler() {
            @Override
            public void onRequest(String method, String path, String protocol) {
                request[0] = method;
                request[1] = path;
                request[2] = protocol;
            }
        });


        parser.parse(httpData.getBytes());

        assertThat(request[0], is("GET"));
        assertThat(request[1], is("/some/path"));
        assertThat(request[2], is("HTTP/1.1"));
    }

}
