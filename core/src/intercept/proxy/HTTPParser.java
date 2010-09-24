package intercept.proxy;

public class HTTPParser {
    private Automaton automaton;
    private RequestHandler requestHandler;

    public interface RequestHandler {
        void onRequest(String method, String path, String protocol);
    }

    public HTTPParser(Automaton automaton, RequestHandler requestHandler) {
        this.automaton = automaton;
        this.requestHandler = requestHandler;
    }

    public void parse(byte[] bytes) {
        for (byte aByte : bytes) {
            automaton.process(aByte);
        }
    }
}
