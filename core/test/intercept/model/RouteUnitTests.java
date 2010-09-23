package intercept.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


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
