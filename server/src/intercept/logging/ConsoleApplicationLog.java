package intercept.logging;

public class ConsoleApplicationLog implements ApplicationLog {
    private boolean verbose = false;

    public void log(String message) {
        System.out.println(message);
    }

    public void trace(String message) {
        if (verbose) {
            System.out.println(message);
        }
    }

    public void setVerbose() {
        this.verbose = true;
    }
}
