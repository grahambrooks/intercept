package intercept.server.components;

import intercept.framework.Command;
import intercept.server.WebContext;

public class NewProxyCommand implements Command {
    public void executeCommand(WebContext context) {
        NewProxyRequestDocument document = new NewProxyRequestDocument();
        context.fillRequestDocument(document);

        context.startNewProxy(document);
        context.redirectTo("/" + document.proxyName);

    }
}
