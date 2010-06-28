package intercept.proxy;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.number.OrderingComparison.greaterThan;

public class AsynchronousSocketTests {
    private static final int TEST_PORT = 8765;

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

    interface AsynchronousSocketBehavior {
        void onReceive(byte[] data, int bytes);

        void onWriteReady(SocketChannel socketChannel);
    }

    class EchoAsynchronousSocketBehavior implements AsynchronousSocketBehavior {
        byte[] pending;

        @Override
        public void onReceive(byte[] data, int bytes) {
            pending = new byte[bytes];
            for (int i = 0; i < bytes; i++) {
                pending[i] = data[i];
            }
        }

        @Override
        public void onWriteReady(SocketChannel socketChannel) {
            if (pending != null) {
                try {
                    socketChannel.write(ByteBuffer.wrap(pending));
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
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

    private class AsynchronousSocket {
        private int port;
        private EchoAsynchronousSocketBehavior socketBehavior;
        private Thread socketThread;
        private boolean running;
        final List<SocketChannel> openChannels = new ArrayList<SocketChannel>();

        public AsynchronousSocket(int port, EchoAsynchronousSocketBehavior socketBehavior) {
            this.port = port;
            this.socketBehavior = socketBehavior;
            running = true;
            final boolean[] threadStarted = new boolean[1];
            threadStarted[0] = false;

            socketThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        ServerSocketChannel serverSocketChannel = openServerSocket();
                        threadStarted[0] = true;

                        while (running) {
                            SocketChannel socketChannel = serverSocketChannel.accept();

                            if (socketChannel != null) {
                                openChannels.add(socketChannel);
                            }

                            processOpenChannels();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }

            });
            socketThread.start();
            while (!threadStarted[0]) {
                try {

                    Thread.sleep(10L);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
            System.out.println("Thread started");
        }

        private void processOpenChannels() {
            for (SocketChannel openChannel : openChannels) {
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                try {
                    int read = openChannel.read(readBuffer);
                    if (read > 0) {
                        byte[] receivedBytes = new byte[read];
                        readBuffer.get(receivedBytes);
                        socketBehavior.onReceive(readBuffer.array(), read);
                    }

                    socketBehavior.onWriteReady(openChannel);
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }

        public void close() {
            running = false;
            for (SocketChannel openChannel : openChannels) {
                try {
                    openChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }
    }
}
