package intercept.model;

public class ResponseDataLogElement extends LogElement {
    private byte[] request;
    private byte[] response;

    public ResponseDataLogElement(byte[] request, byte[] response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public String getMessage() {
        return "REQUEST:\n" + (new String(request))
                + "RESPONSE:\n" + (new String(response != null ? response : new byte[0]));
    }
}
