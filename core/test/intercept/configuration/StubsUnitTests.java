package intercept.configuration;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StubsUnitTests {
    @Test
    public void stubsCanBeMatchedToADomain() {
        Stubs stubs = new Stubs();

        StubRequest stubRequest = new StubRequest();
        stubRequest.setPath("www.thoughtworks.com");

        stubs.createOrUpdateStub(stubRequest);

        assertThat(stubs.isStubbed("www.thoughtworks.com"), is(true));
    }

    @Test
    public void stubs_can_be_matched_to_a_domain() {
        Stubs stubs = new Stubs();

        StubRequest stubRequest = new StubRequest();
        stubRequest.setPath("www.thoughtworks.com");

        stubs.createOrUpdateStub(stubRequest);

        assertThat(stubs.isStubbed("www.thoughtworks.com"), is(true));
    }

    @Test
    public void can_retrieve_response_body_data_for_stub() {
        Stubs stubs = new Stubs();

        StubRequest stubRequest = new StubRequest();
        stubRequest.setPath("www.thoughtworks.com");
        stubRequest.setBody("Agile is the way!");

        stubs.createOrUpdateStub(stubRequest);

        assertThat(stubs.getStubbedResponse("www.thoughtworks.com").getBody(), is("Agile is the way!"));

    }
}
