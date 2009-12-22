package intercept.model;

import java.util.regex.Pattern;

public class Route {
    private final Pattern pattern;
    private final String targetAddress;

    public Route(String pattern, String targetAddress) {
        this.pattern = Pattern.compile(rationalisePattern(pattern));
        this.targetAddress = targetAddress;
    }

    private String rationalisePattern(String pattern) {
        pattern = pattern.replace(".", "\\.");
        pattern = pattern.replace("*", ".*");
        return pattern;
    }

    public boolean matches(String hostname) {
        return pattern.matcher(hostname).matches();
    }

    public String getTargetAddress() {
        return targetAddress;
    }
}
