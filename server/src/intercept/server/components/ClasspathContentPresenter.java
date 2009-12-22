package intercept.server.components;

import intercept.framework.Presenter;
import intercept.server.InterceptServer;
import intercept.server.WebContext;

import java.io.IOException;
import java.io.InputStream;

public class ClasspathContentPresenter implements Presenter {
    public void present(WebContext context) {
        InputStream stream = null;
        try {
            stream = InterceptServer.class.getResourceAsStream(context.getRequestUri().getPath());

            context.renderResponse(stream);
        } finally {
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
