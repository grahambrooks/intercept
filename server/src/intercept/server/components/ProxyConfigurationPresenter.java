package intercept.server.components;

import intercept.framework.Presenter;
import intercept.server.WebContext;

import static intercept.server.WebContext.attribute;
import static intercept.server.WebContext.with;

public class ProxyConfigurationPresenter implements Presenter {

    public void present(WebContext context) {
        context.renderTemplateResponse("proxy",
                with(
                        attribute("proxy", context.getServerConfig()),
                        attribute("stubs", context.getServerConfig().getStubs()),
                        attribute("routes", context.getServerConfig().getOverrides().getRoutes())
                )
        );
    }
}
