package intercept.server.components;

import intercept.configuration.StubRequest;
import intercept.framework.Command;
import intercept.server.WebContext;

public class NewStubCommand implements Command {
    public void executeCommand(WebContext context) {
        StubRequestDocument stubRequest = new StubRequestDocument();

        context.fillRequestDocument(stubRequest);

        context.createOrUpdateStub(stubRequest.getRequest());

        context.redirectTo("/"+context.getServerConfig().getName());
    }
}
