package intercept.server.components;

import intercept.framework.Command;
import intercept.logging.EventLog;
import intercept.server.WebContext;

public class ResetProxyLogCommand implements Command {
    private EventLog logger;

    public ResetProxyLogCommand(EventLog logger) {
        this.logger = logger;
    }
    public void executeCommand(WebContext context) {
        logger.clear();
        context.redirectTo("/" + context.getServerConfig().getName() + "/log");
    }
}
