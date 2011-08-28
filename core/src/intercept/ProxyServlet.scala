package intercept

import tools.nsc.util.HashSet
import javax.servlet._
import org.eclipse.jetty.util.log.Logger
import org.eclipse.jetty.client.HttpClient
import org.eclipse.jetty.http._
import org.eclipse.jetty.util.HostMap

class ProxyServlet extends Servlet {
  protected var _log: Logger = null
  protected var _client: HttpClient = null
  protected var _hostHeader: String = null

  protected val _DontProxyHeaders: HashSet[String] = new HashSet[String]("proxy-headers", 10)
  _DontProxyHeaders("proxy-connection")
  _DontProxyHeaders("connection");
  _DontProxyHeaders("keep-alive");
  _DontProxyHeaders("transfer-encoding");
  _DontProxyHeaders("te");
  _DontProxyHeaders("trailer");
  _DontProxyHeaders("proxy-authorization");
  _DontProxyHeaders("proxy-authenticate");
  _DontProxyHeaders("upgrade");
  protected var _config: ServletConfig = null
  protected var _context: ServletContext = null
  protected var _white: HostMap[PathMap] = new HostMap[PathMap]
  protected var _black: HostMap[PathMap] = new HostMap[PathMap]

  def init(config: ServletConfig) {
    _config = config
    _context = config.getServletContext
    _hostHeader = config.getInitParameter("HostHeader")
  }

  def getServletConfig = _config

  def service(req: ServletRequest, res: ServletResponse) = {
    res.getOutputStream.print("hello")
  }

  def getServletInfo = "Proxy Servlet"

  def destroy() {}
}