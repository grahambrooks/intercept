package intercept.model;

public class ResponseDataLogElement extends LogElement {
    private byte[] request;
    private byte[] response;
    private static final byte[] EMPTY = new byte[0];

    public ResponseDataLogElement(byte[] request, byte[] response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public String getMessage() {
        return "REQUEST:\n" + (new String(request != null ? request : EMPTY))
                + "\nRESPONSE:\n" + (new String(response != null ? response : EMPTY));
    }
}
