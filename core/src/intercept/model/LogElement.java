package intercept.model;

public abstract class LogElement {
    private long time;

    public abstract String getMessage();

    public long time() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
