package intercept.utils;

import intercept.logging.EventLog;

public interface Function<T> {
    T execute(EventLog eventLog);
}
