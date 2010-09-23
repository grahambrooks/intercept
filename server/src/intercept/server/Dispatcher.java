package intercept.server;

import intercept.framework.Command;
import intercept.framework.Presenter;
import intercept.framework.UriMatcher;

import java.util.LinkedList;
import java.util.List;

public class Dispatcher {
    public final List<DispatchEntry<Presenter>> presenterRegistry;
    public final List<DispatchEntry<Command>> commandRegistry;

    public Dispatcher() {
        presenterRegistry = new LinkedList<DispatchEntry<Presenter>>();
        commandRegistry = new LinkedList<DispatchEntry<Command>>();
    }

    public void dispatchGetRequest(WebContext context) {
        context.present(presenterRegistry, context);
    }

    public void dispatchPostRequest(WebContext context) {
        context.executeCommand(commandRegistry, context);
    }

    public void register(UriMatcher matcher, Presenter presenter) {
        presenterRegistry.add(new DispatchEntry(matcher, presenter));
    }

    public void register(UriMatcher matcher, Command command) {
        commandRegistry.add(new DispatchEntry(matcher, command));
    }


    public void register(UriMatcher matcher, Presenter presenter, Command command) {
        register(matcher, presenter);
        register(matcher, command);
    }
}
