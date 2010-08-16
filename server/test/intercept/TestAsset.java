package intercept;

import intercept.configuration.InterceptConfiguration;
import intercept.logging.ConsoleApplicationLog;
import intercept.server.InterceptServer;
import intercept.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.ArrayList;
import java.util.List;

abstract class TestAsset {
    protected List<TestAsset> children;
    protected static InterceptServer interceptServer;

    public final static TestAsset intercept() {
        return new TestAsset() {
            InterceptConfiguration config;

            @Override
            public void itemConstruct(TestContext ctx) {
                ConsoleApplicationLog log = new ConsoleApplicationLog();
                interceptServer = new InterceptServer(log);
                ctx.putIntercept(interceptServer);
                config = new InterceptConfiguration(log);

                interceptServer.start(config);
            }

            @Override
            public void itemClose() {
                if (interceptServer != null) {
                    interceptServer.stop(config);
                }
            }
        };
    }

    public static TestAsset HTMLUnit() {
        return new TestAsset() {
            WebDriver driver;

            @Override
            public void itemConstruct(TestContext ctx) {
                System.out.println("Starting htmlunit");
                driver = ctx.configure(new HtmlUnitDriver());
                ctx.driver = driver;
            }

            @Override
            public void itemClose() {
                if (driver != null) {
                    driver.quit();
                }
                driver = null;
            }
        };
    }

    TestAsset() {
        this.children = new ArrayList<TestAsset>();
    }

    protected abstract void itemConstruct(TestContext ctx);

    public void construct(TestContext ctx) {
        itemConstruct(ctx);
        for (TestAsset child : children) {
            child.construct(ctx);
        }
    }

    protected abstract void itemClose();

    public void close() {
        for (TestAsset child : children) {
            child.close();
        }
        itemClose();
    }

    public TestAsset with(TestAsset child) {
        this.children.add(child);
        return this;
    }

    public static InterceptServer interceptInstance() {
        return interceptServer;
    }

}
