package intercept;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HomePageAcceptanceTests {

    @BeforeClass
    public static void envSetup() {
        final TestContext testContext;
        testContext = TestContext.using(TestAsset.intercept().with(TestAsset.HTMLUnit()));
        testContext.start();

        TestContext.set(testContext);
    }

    @AfterClass
    public static void envCleanup() {
        TestContext.cleanup();
    }

    @Test
    public void homePageTitleIsSet() {
        TestContext ctx = TestContext.get();
        ctx.driver().get(TestAsset.interceptInstance().uri("/"));
        assertThat(ctx.driver().getTitle(), is("Intercept - Server"));
    }

//    @Test
//    public void homePageWeightShouldBe200Bytes() {
//        TestContext ctx = TestContext.get();
//        ctx.driver().get(TestAsset.interceptInstance().uri("/"));
//
//        Long aLong = ctx.proxy().response(Utils.pageWeight);
//        assertThat(aLong, is(200L));
//    }

    @Test
    public void homepageContainsDocumentationLink() {
        TestContext ctx = TestContext.get();
        ctx.driver().get(TestAsset.interceptInstance().uri("/"));
        WebElement element = ctx.driver().findElement(By.linkText("Intercept documentation"));

        assertThat(element.getAttribute("href"), is("/doc/index.html"));
    }

    @Test
    public void homepageContainsLinkToCreateNewProxy() {
        TestContext ctx = TestContext.get();
        ctx.driver().get(TestAsset.interceptInstance().uri("/"));
        WebElement element = ctx.driver().findElement(By.linkText("New Proxy"));

        assertThat(element.getAttribute("href"), is("/proxy/new"));
    }
}
