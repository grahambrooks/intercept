package intercept.server.components;

import intercept.framework.Presenter;
import intercept.logging.EventLog;
import intercept.server.WebContext;
import static intercept.server.WebContext.attribute;
import static intercept.server.WebContext.with;

public class ProxyLogPresenter implements Presenter {
    private EventLog logs;

    public ProxyLogPresenter(EventLog logs) {
        this.logs = logs;
    }

    public void present(WebContext context) {
        context.renderTemplateResponse("proxy-log",
            with(
                attribute("proxy", context.getServerConfig()),
                attribute("logs", logs.getEntries())
            )
        );
    }
}
