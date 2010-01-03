package intercept.utils;

public class MillisecondTimer implements EventTimer {
    private long start;

    public MillisecondTimer() {
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
