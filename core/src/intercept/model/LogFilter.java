package intercept.model;


public interface LogFilter<T> {
    public void filter(T element, FilterTarget<T> result);
}
