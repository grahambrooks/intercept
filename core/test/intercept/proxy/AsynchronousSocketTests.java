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

public class AsynchronousSocketTests {
    private static final int TEST_PORT = 8765;

    class EchoAsynchronousSocketBehavior implements AsynchronousSocketBehavior {
        byte[] pending;

        @Override
        public void onReceive(byte[] data, int bytes) {
            pending = new byte[bytes];
            System.arraycopy(data, 0, pending, 0, bytes);
        }

        @Override
        public void onWriteReady(SocketChannel socketChannel) {
            if (pending != null) {
                try {
                    socketChannel.write(ByteBuffer.wrap(pending));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                pending = null;
            }
        }
    }

    @Test
    public void canConnectToAsynchronousSocketAndExchangeData() throws IOException, InterruptedException {
        AsynchronousSocket socket = new AsynchronousSocket(TEST_PORT, new EchoAsynchronousSocketBehavior());

        Socket clientSocket = openClientSocket();

        byte[] messageBytes = "Hello all".getBytes();
        clientSocket.getOutputStream().write(messageBytes);

        byte[] received = new byte[1024];

        int read = 0;
        while (read <= 0) {
            Thread.sleep(10L);
            read = clientSocket.getInputStream().read(received);
        }

        assertThat(new String(received, 0, read), is("Hello all"));
        clientSocket.close();
        socket.close();
    }

    private Socket openClientSocket() throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(TEST_PORT));
        return socket;
    }

    private ServerSocketChannel openServerSocket() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(TEST_PORT));
        return serverSocketChannel;
    }

}
