package intercept

import org.eclipse.jetty.server.handler.HandlerCollection
import org.eclipse.jetty.server.handler.ConnectHandler
import org.eclipse.jetty.server.nio.SelectChannelConnector
import org.eclipse.jetty.servlet.ServletContextHandler
import org.eclipse.jetty.servlet.ServletHolder
import org.eclipse.jetty.server.Server

class ProxyServer {

  val server = new Server()

  private def setupProxyServlet(handlers: HandlerCollection) {
    val context = new ServletContextHandler(handlers, "/", ServletContextHandler.SESSIONS);
    val proxyServlet = new ServletHolder(classOf[ProxyServlet]);
    context.addServlet(proxyServlet, "/*");

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

  def run(port: Int) {
    setupConnectors(port)

    setupProxyServlet(createHandlers)

    server.start();

  }
}