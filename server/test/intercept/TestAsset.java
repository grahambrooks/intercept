package intercept;

import intercept.configuration.InterceptConfiguration;
import intercept.logging.ConsoleApplicationLog;
import intercept.server.InterceptServer;
import intercept.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.safari.SafariDriver;

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

                ctx.setWebProxy("http://localhost", config.getConfigurationPort());
                new Thread() {
                    @Override
                    public void run() {
                        super.run();    //To change body of overridden methods use File | Settings | File Templates.
                        interceptServer.start(config);
                    }
                }.start();
                Utils.sleep(400);
            }

            @Override
            public void itemClose() {
                if (interceptServer != null) {
                    interceptServer.stop(config);
                }
            }
        };
    }

    public static final TestAsset firefox() {
        return new TestAsset() {
            WebDriver driver;

            @Override
            public void itemConstruct(TestContext ctx) {
                System.out.println("Starting firefox");
                FirefoxProfile profile = new FirefoxProfile();

                profile.setPreference("network.proxy.http", "localhost");
                profile.setPreference("network.proxy.http_port", 2001);
                profile.setPreference("network.proxy.type", 1);

//                    driver = new FirefoxDriver(profile);
                driver = new FirefoxDriver();
                ctx.driver = driver;
            }

            @Override
            public void itemClose() {
                if (driver != null) {
                    driver.quit();
                    driver.close();
                }
                driver = null;
            }
        };
    }
    public static TestAsset HTMLUnit() {
        return new TestAsset() {
            WebDriver driver;

            @Override
            public void itemConstruct(TestContext ctx) {
                System.out.println("Starting htmlunit");
                driver = new HtmlUnitDriver();
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

    public static final TestAsset safari() {
        return new TestAsset() {
            WebDriver driver;

            @Override
            public void itemConstruct(TestContext ctx) {
                FirefoxProfile profile = new FirefoxProfile();

                profile.setPreference("network.proxy.http", "localhost");
                profile.setPreference("network.proxy.http_port", 2001);
                profile.setPreference("network.proxy.type", 1);

//                    driver = new FirefoxDriver(profile);
                driver = new SafariDriver();
                ctx.driver = driver;
            }

            @Override
            public void itemClose() {
                driver.quit();
                driver.close();
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

    public static InterceptServer middlemanInstance() {
        return interceptServer;
    }

}
