package intercept.proxy;

import intercept.logging.EventLog;
import intercept.logging.EventLogger;
import intercept.utils.EventTimer;
import org.junit.Test;

import java.net.InetAddress;
import java.net.Socket;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProxyChannelUnitTests {
    @Test
    public void canInstantiateProxyChannel() {
        new ProxyChannel(null, null, null, null);
    }

    @Test
    public void logResponseRecordsElapsedTime() throws Exception {
        EventLog log = new EventLog();
        EventLogger logger = new EventLogger(log);

        Socket socket = mock(Socket.class);
        InetAddress localHost = InetAddress.getLocalHost();
        String address = localHost.getHostAddress();
        when(socket.getInetAddress()).thenReturn(localHost);
        when(socket.getLocalPort()).thenReturn(8080);

        ProxyChannel proxyChannel = new ProxyChannel(socket, null, logger, null);
        EventTimer eventTimer = new EventTimer() {
            @Override
            public long elapsed() {
                return 0;
            }

            @Override
            public String toString() {
                return "4 ms";
            }
        };

        proxyChannel.logResponse(eventTimer, 1234, new HTTPRequest());

        assertThat(log.getEntries().size(), equalTo(1));
        assertThat(log.getEntries().get(0).elements.get(1).getMessage(), is("Response from " + address + ":8080 to unknown:80 0 bytes sent 1234 bytes returned in 4 ms"));
    }
}
