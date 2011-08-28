package intercept

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.eclipse.jetty.client.{Address, HttpClient}

class ProxyServerSpec extends FlatSpec with ShouldMatchers {
  "A ProxyServer" should "be instantiatable" in {
    new ProxyServer()
  }

  "A ProxyServer" should "trasparently connect to server" in {
    val testServer = new TestServer()
    testServer.run(1234)
    val proxyServer = new ProxyServer()
    proxyServer.run(1235)

    val client = new HttpClient()

    client.setProxy(Address.from("http://localhost:1235"))
  }
}
