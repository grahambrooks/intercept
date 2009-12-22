package intercept.server;

import intercept.model.UriMatcher;

class DispatchEntry<T>{
    UriMatcher matcher;
    T handler;

    public DispatchEntry(UriMatcher matcher, T handler) {
        this.matcher = matcher;
        this.handler = handler;
    }
}
