package intercept.proxy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

class AsynchronousSocket {
    private AsynchronousSocketBehavior socketBehavior;
    private Thread socketThread;
    private boolean running;
    final List<SocketChannel> openChannels = new ArrayList<SocketChannel>();

    public AsynchronousSocket(final int port, AsynchronousSocketBehavior socketBehavior) {
        this.socketBehavior = socketBehavior;
        running = true;
        final boolean[] threadStarted = new boolean[1];
        threadStarted[0] = false;

        socketThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServerSocketChannel serverSocketChannel = openServerSocket(port);
                    threadStarted[0] = true;

                    while (running) {
                        SocketChannel socketChannel = serverSocketChannel.accept();

                        if (socketChannel != null) {
                            openChannels.add(socketChannel);
                        }

                        processOpenChannels();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
        socketThread.start();
        while (!threadStarted[0]) {
            try {
                Thread.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
                e.printStackTrace();
            }
        }
    }

    private ServerSocketChannel openServerSocket(int port) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        return serverSocketChannel;
    }


    public void close() {
        running = false;
        for (SocketChannel openChannel : openChannels) {
            try {
                openChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
