package intercept.server.components;

import intercept.framework.Presenter;
import intercept.server.WebContext;

public class NewProxyPresenter implements Presenter {
    public void present(WebContext context) {
        context.renderTemplateResponse("new-proxy");
    }
}
