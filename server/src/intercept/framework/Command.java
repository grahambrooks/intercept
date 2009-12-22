package intercept.framework;

import intercept.server.WebContext;

public interface Command {
    void executeCommand(WebContext context);
}
