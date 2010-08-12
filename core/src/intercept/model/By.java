package intercept.model;

public class By {
    public static <T> LogFilter<T> type(final Class<T> type) {
        return new LogFilter<T>() {
            @Override
            public void filter(T element, FilterTarget<T> result) {
                if (type.isInstance(element)) {
                    result.add(element);
                }
            }
        };
    }
}
