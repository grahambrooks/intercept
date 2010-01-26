package intercept.model;

public class ResponseDataLogElement extends LogElement {
    private byte[] request;
    private byte[] response;
    private static final byte[] EMPTY = new byte[0];

    public ResponseDataLogElement(byte[] request, byte[] response) {
        this.request = request == null ? EMPTY : request.clone();
        this.response = response == null ? EMPTY : response.clone();
    }

    @Override
    public String getMessage() {
        return "REQUEST:\n" + (new String(request))
                + "\nRESPONSE:\n" + (new String(response));
    }
}
