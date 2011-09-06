package intercept

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.{ServletHolder, ServletContextHandler}
import org.eclipse.jetty.server.handler.{ConnectHandler, HandlerCollection}
import org.eclipse.jetty.server.nio.SelectChannelConnector

class TestServer {
  val server = new Server()

  private def setupProxyServlet(handlers: HandlerCollection) {
    val context = new ServletContextHandler(handlers, "/", ServletContextHandler.SESSIONS);
    val testServlet = new ServletHolder(classOf[TestServlet]);
    context.addServlet(testServlet, "/*");

    handlers.addHandler(new ConnectHandler());
  }

  private def createHandlers: HandlerCollection = {
    val handlers = new HandlerCollection();
    server.setHandler(handlers);
    handlers
  }

  private def setupConnectors(port: Int) {
    val connector = new SelectChannelConnector();
    connector.setPort(port);
    server.addConnector(connector);
  }

  def stop(port: Int) {
    setupConnectors(port)

    setupProxyServlet(createHandlers)

    server.start();

  }

  def stop {
    server.stop()
  }

}