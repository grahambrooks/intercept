package intercept.server.components;

import intercept.configuration.InterceptConfiguration;
import intercept.framework.Presenter;
import intercept.server.WebContext;
import static intercept.server.WebContext.attribute;
import static intercept.server.WebContext.with;

public class HomePagePresenter implements Presenter {
    private InterceptConfiguration interceptConfiguration;

    public HomePagePresenter(InterceptConfiguration interceptConfiguration) {
        this.interceptConfiguration = interceptConfiguration;
    }

    public void present(WebContext context) {
        context.renderTemplateResponse("home",
                with(
                        attribute("proxies", context.getRunningProxies()),
                        attribute("port", interceptConfiguration.getConfigurationPort())
                )
        );
    }
}
