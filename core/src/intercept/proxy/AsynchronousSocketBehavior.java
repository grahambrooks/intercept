package intercept.proxy;

import java.nio.channels.SocketChannel;

interface AsynchronousSocketBehavior {
    void onReceive(byte[] data, int bytes);

    void onWriteReady(SocketChannel socketChannel);
}
