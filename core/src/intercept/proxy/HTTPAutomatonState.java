package intercept.proxy;

public enum HTTPAutomatonState {
    HEADER_PENDING,
    READING_HEADER,
    EOH_PENDING,
    BODY_PENDING,
    READING_BODY,
}
