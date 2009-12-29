package intercept.utils;

public class Clock {
    private static boolean fixed = false;
    private static long fixedTime = 0L;

    public static long nanoTime() {
        return fixed ? fixedTime : System.nanoTime();
    }

    public static void setTime(long fixedTime) {
        Clock.fixed = true;
        Clock.fixedTime = fixedTime;
    }
}
