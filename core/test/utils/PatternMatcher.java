package utils;

import org.hamcrest.BaseMatcher;

public class PatternMatcher extends BaseMatcher<String> {
    private final String pattern;

    public PatternMatcher(String pattern) {
        this.pattern = pattern;
    }

    public boolean matches(Object o) {
        return ((String) o).matches(pattern);

    }

    public static PatternMatcher matches(String pattern) {
        return new PatternMatcher(pattern);
    }

    public void describeTo(org.hamcrest.Description description) {
        description.appendText("matches pattern=" + this.pattern);
    }
}
