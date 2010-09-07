package intercept.utils;

public interface Function<T, U> {
    T execute(U u);
}
