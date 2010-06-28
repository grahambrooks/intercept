package intercept.proxy;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.number.OrderingComparison.greaterThan;

public class AsynchronousSocketTests {
    @Test
    public void serverSocketAcceptsConnections() throws IOException {
        ServerSocketChannel serverSocketChannel = openServerSocket();

        assertThat("Server socket is not blocking", !serverSocketChannel.isBlocking());

        Socket socket = openClientSocket();

        assertThat("Client socket connects", socket.isConnected());

        SocketChannel channel = serverSocketChannel.accept();

        assertThat("Socket not null", channel != null);

        socket.close();
        serverSocketChannel.close();
    }

    @Test
    public void canReceiveDataAsynchronously() throws IOException {
        ServerSocketChannel serverSocketChannel = openServerSocket();
        Socket socket = openClientSocket();

        byte[] messageBytes = "Hello all".getBytes();
        socket.getOutputStream().write(messageBytes);

        SocketChannel channel = serverSocketChannel.accept();
        ByteBuffer readBuffer = ByteBuffer.allocate(messageBytes.length + 1);

        int bytesRead = channel.read(readBuffer);

        assertThat(bytesRead, is(greaterThan(1)));

        readBuffer.flip();

        byte[] read = new byte[bytesRead];
        readBuffer.get(read);

        assertThat("Received text is the same as sent", read, is(messageBytes));

        socket.close();
        serverSocketChannel.close();
    }

    private Socket openClientSocket() throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(8080));
        return socket;
    }

    private ServerSocketChannel openServerSocket() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        return serverSocketChannel;
    }
}
