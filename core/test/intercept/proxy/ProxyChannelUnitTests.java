package intercept.proxy;

import intercept.logging.EventLog;
import intercept.logging.EventLogger;
import intercept.utils.EventTimer;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.net.InetAddress;
import java.net.Socket;

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
        when(socket.getInetAddress()).thenReturn(InetAddress.getLocalHost());

        ProxyChannel proxyChannel = new ProxyChannel(socket, null, logger, null);
        proxyChannel.logResponse(new EventTimer(), 1234, new HTTPRequest());

        assertThat(log.getEntries().size() == 1, is(true));
        assertThat(log.getEntries().get(0).elements.get(3).getMessage(), is("0 bytes sent"));
        assertThat(log.getEntries().get(0).elements.get(4).getMessage(), is("1234 bytes returned"));
    }
}
