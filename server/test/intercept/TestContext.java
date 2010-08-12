package intercept;

import intercept.proxy.InterceptProxy;
import intercept.proxy.ProxyServer;
import intercept.server.InterceptServer;
import intercept.utils.Block;
import org.openqa.selenium.WebDriver;

class TestContext {
    TestAsset asset;
    private InterceptServer interceptServer;
    WebDriver driver;

    public TestContext(TestAsset asset) {
        this.asset = asset;
    }

    public static TestContext using(TestAsset asset) {
        return new TestContext(asset);
    }

    public void verify(Block<TestContext> block) {
        try {
            asset.construct(this);

            block.yield(this);
        } finally {
            asset.close();
        }
    }

    public WebDriver driver() {
        return driver;
    }

    public void putIntercept(InterceptServer interceptServer) {
        this.interceptServer = interceptServer;
    }

    public ProxyServer proxy() {
        return InterceptProxy.getRunningProxy(0);
    }
}
