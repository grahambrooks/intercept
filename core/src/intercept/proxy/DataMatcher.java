package intercept.proxy;

/**
 * Compares the data value supplied against rules defined in implementation classes.
 *
 * @param <D> Data value to be compared against the rules.
 */
interface DataMatcher<D> {
    boolean matches(D data);
}
