package intercept.utils;

public class EventTimer {

    private long start;

    public EventTimer() {
        this.start = System.currentTimeMillis();
    }

    public long startTime() {
        return this.start;
    }

    public long elapsed() {
        return System.currentTimeMillis() - this.start;
    }

    @Override
    public String toString() {
        return elapsed() + " ms";
    }
}
