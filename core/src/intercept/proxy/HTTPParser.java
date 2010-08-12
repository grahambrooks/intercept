package intercept.proxy;

public class HTTPParser {
    private HTTPAutomaton automaton;

    public interface RequestHandler{
        void onRequest(String method, String path, String protocol);
    }

    private RequestHandler requestHandler;

    public HTTPParser() {
        this.automaton = new HTTPAutomaton ();
    }

    public void set(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    public void parse(byte[] bytes) {
        for (byte aByte : bytes) {
            automaton.process(aByte);
        }
    }
}
