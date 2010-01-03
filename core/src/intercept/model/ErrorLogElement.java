package intercept.model;

public class ErrorLogElement extends LogElement {
    private String message;
    private Exception exception;

    public ErrorLogElement(String message, Exception exception) {
        this.message = message;
        this.exception = exception;
    }

    @Override
    public String getMessage() {
        return message + (exception == null ? "" : exception.toString());
    }
}
