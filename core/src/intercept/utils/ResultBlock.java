package intercept.utils;

public interface ResultBlock<ParameterType, ResultType> {
    ResultType yield(ParameterType t);
    ResultType result();
}
