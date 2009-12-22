package intercept.server.components;

import intercept.framework.Command;
import intercept.server.WebContext;

public class NewStubCommand implements Command {
    public void executeCommand(WebContext context) {
        StubRequest stubRequest = new StubRequest();

        context.fillRequestDocument(stubRequest);

        context.createOrUpdateStub(stubRequest);

        context.redirectTo("/"+context.getServerConfig().getName());
    }
}
