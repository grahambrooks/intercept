package intercept.logging;

public interface ApplicationLog {
    public static final ApplicationLog NullApplicationLog = new ApplicationLog(){
        @Override
        public void log(String message) {
        }

        @Override
        public void trace(String message) {
        }
    };
    public void log(String message);
    void trace(String message);
}
