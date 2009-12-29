package intercept.model;

public class SimpleLogElement extends LogElement {
    private String message;

    public SimpleLogElement(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
