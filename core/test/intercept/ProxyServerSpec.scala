package intercept

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class ProxyServerSpec extends FlatSpec with ShouldMatchers {
  "A ProxyServer" should "be instantiatable" in {
    new ProxyServer()
  }
}
