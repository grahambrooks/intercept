package intercept.model;

public class By {
    public static LogFilter<LogElement> type(final Class<?> type) {
        return new LogFilter<LogElement>() {
            @Override
            public void filter(LogElement element, FilterTarget<LogElement> result) {
                if (type.isInstance(element)) {
                    result.add(element);
                }
            }
        };
    }
}
