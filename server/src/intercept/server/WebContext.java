package intercept.server;

import com.sun.net.httpserver.HttpExchange;
import intercept.configuration.ProxyConfig;
import intercept.configuration.StubRequest;
import intercept.framework.Command;
import intercept.framework.Presenter;
import intercept.framework.RequestDocument;
import intercept.framework.WebServer;
import intercept.model.UriMatcher;
import intercept.server.components.NewProxyRequestDocument;
import intercept.server.components.TemplateAttribute;
import intercept.server.components.TemplateAttributes;
import intercept.utils.Block;
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class WebContext {
    private WebServer server;
    private final HttpExchange httpExchange;
    StringTemplateGroup templateGroup;

    public WebContext(WebServer server, HttpExchange httpExchange) {
        this.server = server;
        this.httpExchange = httpExchange;
        this.templateGroup = new StringTemplateGroup("view");
    }

    public static TemplateAttribute attribute(final String name, final Object value) {
        return new TemplateAttribute(name, value);
    }

    public static TemplateAttributes with(final TemplateAttribute... attributes) {
        return new TemplateAttributes(attributes);
    }

    public void renderTemplateResponse(String templateName) {
        renderTemplateResponse(templateName, new TemplateAttributes(new TemplateAttribute[]{}));
    }

    public void renderTemplateResponse(String templateName, TemplateAttributes templateAttributes) {
        try {
            StringTemplate template = templateGroup.getInstanceOf("intercept/templates/" + templateName);

            templateAttributes.applyTo(template);

            writeHtmlResponse(200, template.toString());

        } catch (Exception e) {
            handleException(e);
        }
    }


    public void renderResponse(InputStream inputStream) {
        try {
            byte[] buffer = new byte[1024];
            OutputStream responseStream = httpExchange.getResponseBody();

            httpExchange.sendResponseHeaders(200, inputStream.available());

            int read = inputStream.read(buffer);
            while (read >= 0) {
                responseStream.write(buffer, 0, read);
                read = inputStream.read(buffer);
            }

            responseStream.close();
            inputStream.close();
        } catch (IOException e) {
            handleException(e);
        }

    }


    private void handleException(Exception e) {
        try {
            httpExchange.sendResponseHeaders(500, 0L);
            httpExchange.getResponseBody().close();
        } catch (IOException e1) {
            throw new RuntimeException("Can't even tell people about the errors", e);
        }
    }

    private void writeHtmlResponse(int code, String responseText) {
        httpExchange.getResponseHeaders().set("content-type", "text/html");
        writeResponse(code, responseText);
    }

    private void writeResponse(int code, String responseText) {
        try {
            byte[] responseBytes = responseText.getBytes();
            httpExchange.sendResponseHeaders(code, responseBytes.length);
            OutputStream responseStream = httpExchange.getResponseBody();
            responseStream.write(responseBytes);
            responseStream.close();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void redirectTo(String uri) {
        try {
            ArrayList<String> values = new ArrayList<String>();
            values.add(uri);
            httpExchange.getResponseHeaders().put("Location", values);
            httpExchange.sendResponseHeaders(302, -1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public WebServer getServer() {
        return server;
    }

    public void present(List<DispatchEntry<Presenter>> routeRegistry, WebContext context) {
        for (DispatchEntry<Presenter> entry : routeRegistry) {
            if (context.requestMatchesUriPattern(entry.matcher)) {
                entry.handler.present(context);
                return;
            }
        }
        context.noHandlerForRequest();
    }

    public void executeCommand(List<DispatchEntry<Command>> routeRegistry, WebContext context) {
        for (DispatchEntry<Command> entry : routeRegistry) {
            if (context.requestMatchesUriPattern(entry.matcher)) {
                entry.handler.executeCommand(context);
                return;
            }

        }
        context.noHandlerForRequest();
    }

    private void noHandlerForRequest() {
        throw new NoRouteException(getRequestUri());

    }

    private boolean requestMatchesUriPattern(UriMatcher pattern) {
        return pattern.matches(getRequestUri());
    }

    public URI getRequestUri() {
        return httpExchange.getRequestURI();
    }

    public ProxyConfig getServerConfig() {
        return getServer().getConfig();
    }

    public List<ProxyConfig> getRunningProxies() {
        return getServer().getRunningProxies();
    }

    public void eachLine(InputStream input, Block<String> visitor) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        try {
            String line = reader.readLine();
            while (line != null) {
                String[] parameters = line.split("&");
                for (String parameter : parameters) {
                    visitor.yield(parameter.replaceAll("\\+", " "));
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void fillRequestDocument(final RequestDocument document) {
        InputStream input = httpExchange.getRequestBody();
        try {
        eachLine(input, new Block<String>() {
            public void yield(String item) {
                String[] elements = item.split("=");
                document.set(elements[0], elements[1]);
            }
        });
        } finally {
            if (input != null){
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void createOrUpdateStub(StubRequest stubRequest) {
        getServerConfig().createOrUpdateStub(stubRequest);
    }

    public void startNewProxy(NewProxyRequestDocument document) {
        getServer().startNewProxy(document.getProxyName(), document.getProxyPort());
    }
}
