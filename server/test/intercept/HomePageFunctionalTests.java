package intercept;

import intercept.utils.Block;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;


public class HomePageFunctionalTests {

    @Test
    public void canViewHomepage() {
        TestContext.using(TestAsset.intercept().with(TestAsset.firefox())).verify(new Block<TestContext>() {
            public void yield(TestContext ctx) {
                ctx.driver().get(TestAsset.middlemanInstance().uri("/"));
                assertThat(ctx.driver().getTitle(), is("Middleman - Server"));
            }
        });
    }
}
