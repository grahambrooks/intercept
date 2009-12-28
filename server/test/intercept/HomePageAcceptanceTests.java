package intercept;

import intercept.utils.Block;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
                ctx.driver().get(TestAsset.interceptInstance().uri("/"));
                assertThat(ctx.driver().getTitle(), is("Intercept - Server"));
            }
        });
    }

    @Test
    public void homepageContainsDocumentationLink() {
        testContext.verify(new Block<TestContext>() {
            public void yield(TestContext ctx) {
                ctx.driver().get(TestAsset.interceptInstance().uri("/"));
                WebElement element = ctx.driver().findElement(By.linkText("Intercept documentation"));

                assertThat(element.getAttribute("href"), is("/doc/index.html"));
            }
        });
    }

    @Test
    public void homepageContainsLinkToCreateNewProxy() {
        testContext.verify(new Block<TestContext>() {
            public void yield(TestContext ctx) {
                ctx.driver().get(TestAsset.interceptInstance().uri("/"));
                WebElement element = ctx.driver().findElement(By.linkText("New Proxy"));

                assertThat(element.getAttribute("href"), is("/proxy/new"));
            }
        });
    }
}
