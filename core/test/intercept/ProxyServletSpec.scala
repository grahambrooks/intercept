package intercept

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import com.meterware.servletunit.ServletRunner
import com.meterware.httpunit.WebResponse


class ProxyServletSpec extends FlatSpec with ShouldMatchers {
  "A ProxyServlet" should "responds to requests" in {
    val sr = new ServletRunner()
    sr.registerServlet("/", classOf[ProxyServlet].getName)

    val client = sr.newClient()

    val response: WebResponse = client.getResponse("http://domain/")
    response.getResponseCode should equal(200)
    response.getText should equal("hello")
  }
}