package intercept;

import intercept.utils.Block;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePageAcceptanceTests {
    static TestContext testContext;

    @BeforeClass
    public static void setupEnvironment() {
        testContext = TestContext.using(TestAsset.intercept().with(TestAsset.HTMLUnit()));
    }

    @Test
    public void homePageTitleIsSet() {
        testContext.verify(new Block<TestContext>() {
            public void yield(TestContext ctx) {
                ctx.driver().get(TestAsset.middlemanInstance().uri("/"));
                assertThat(ctx.driver().getTitle(), is("Middleman - Server"));
            }
        });
    }

    @Test
    public void homepageContainsDocumentationLink() {
        testContext.verify(new Block<TestContext>() {
            public void yield(TestContext ctx) {
                ctx.driver().get(TestAsset.middlemanInstance().uri("/"));
                WebElement element = ctx.driver().findElement(By.linkText("Middleman documentation"));

                assertThat(element.getAttribute("href"), is("/doc/index.html"));
            }
        });
    }

    @Test
    public void homepageContainsLinkToCreateNewProxy() {
        testContext.verify(new Block<TestContext>() {
            public void yield(TestContext ctx) {
                ctx.driver().get(TestAsset.middlemanInstance().uri("/"));
                WebElement element = ctx.driver().findElement(By.linkText("New Proxy"));

                assertThat(element.getAttribute("href"), is("/proxy/new"));
            }
        });
    }
}
