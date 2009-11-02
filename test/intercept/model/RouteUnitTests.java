package intercept.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;


public class RouteUnitTests {
    @Test
    public void routeMatchesDomainNames() {
        Route route = new Route("www.umisiri.com", "www.target.com");

        assertThat(route.matches("www.umisiri.com"), equalTo(true));
    }

    @Test
    public void routeDoesNotMatchWrongDomain() {
        Route route = new Route("www.umisiri.com", "www.target.com");

        assertThat(route.matches("www.umisir.com"), equalTo(false));
    }
}
