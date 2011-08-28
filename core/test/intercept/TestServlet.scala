package intercept

import javax.servlet.{ServletConfig, ServletResponse, ServletRequest, Servlet}

class TestServlet extends Servlet {
  protected var _config: ServletConfig = null

  def init(config: ServletConfig) {
    _config = config
  }

  def getServletConfig = _config

  def service(request: ServletRequest, response: ServletResponse) {
    response.getOutputStream.print("Test")
  }

  def getServletInfo = ""

  def destroy() {}
}