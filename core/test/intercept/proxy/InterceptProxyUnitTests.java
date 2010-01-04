package intercept.proxy;

import intercept.configuration.DefaultProxyConfig;
import intercept.configuration.ProxyConfig;
import intercept.logging.ApplicationLog;
import intercept.utils.Block;
import org.junit.Before;
import org.junit.Test;

public class InterceptProxyUnitTests {
    ApplicationLog applicationLog;
    ProxyConfig config;

    @Before
    public void setup() {
        applicationLog = new ApplicationLog() {
            @Override
            public void log(String message) {
            }

            @Override
            public void trace(String message) {
            }
        };
        config = new DefaultProxyConfig();
        config.setName("test");
        config.setPort(2000);
    }

    @Test
    public void canStartAndStopAProxy() {
        ProxyServer proxyServer = InterceptProxy.startProxy(config, applicationLog);
        InterceptProxy.stopProxy(proxyServer);

        InterceptProxy.eachProxy(new Block<ProxyServer>() {
            @Override
            public void yield(ProxyServer item) {
                throw new RuntimeException("Should not be called");
            }
        });
    }

    @Test
    public void shutdownClosesProxies() {
        InterceptProxy.startProxy(config, applicationLog);
        InterceptProxy.shutdown();

        InterceptProxy.eachProxy(new Block<ProxyServer>() {
            @Override
            public void yield(ProxyServer item) {
                throw new RuntimeException("Should not be called");
            }
        });

    }
}
