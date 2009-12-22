package intercept.logging;

public interface ApplicationLog {
    public void log(String message);
    void trace(String message);
}
