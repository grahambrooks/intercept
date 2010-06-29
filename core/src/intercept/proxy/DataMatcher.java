package intercept.proxy;

interface DataMatcher<D> {
    boolean matches(D data);
}
