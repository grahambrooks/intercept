package intercept.server.components;

import intercept.configuration.InterceptConfiguration;
import intercept.server.WebContext;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HomePagePresenterUnitTests {
    TemplateAttributes templateParameters(final String... names) {
        return argThat(new ArgumentMatcher<TemplateAttributes>() {
            @Override
            public boolean matches(Object o) {
                TemplateAttributes attributes = (TemplateAttributes) o;
                for (String name : names) {
                    if (!attributes.contains(name)) {
                        return false;
                    }
                }
                return true;
            }
        });
    }

    @Test
    public void test() {
        InterceptConfiguration config = mock(InterceptConfiguration.class);
        HomePagePresenter presenter = new HomePagePresenter(config);

        WebContext context = mock(WebContext.class);
        presenter.present(context);

        verify(context).renderTemplateResponse(eq("home"), templateParameters("proxies", "port"));
    }
}
