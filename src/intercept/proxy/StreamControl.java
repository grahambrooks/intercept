package intercept.proxy;

public class StreamControl {
    private int expectedBytes;
    private boolean waitForDisconnect;

    public StreamControl(int expectedBytes, boolean waitForDisconnect) {
        this.expectedBytes = expectedBytes;
        this.waitForDisconnect = waitForDisconnect;
    }

    public boolean waitForDisconnect() {
        return this.waitForDisconnect;
    }

    public boolean dataExpected() {
        return expectedBytes > 0 || waitForDisconnect();
    }

    public boolean moreDataExpected(int recievedBytes) {
        return recievedBytes < expectedBytes || waitForDisconnect();
    }

    public String toString() {
        return "Stream control expected " + expectedBytes + " WaitForDisconnect is " + waitForDisconnect;
    }

    public int expected() {
        return this.expectedBytes;
    }
}
