package intercept.utils;

import intercept.logging.EventLog;
import intercept.model.By;
import intercept.model.ResponseDataLogElement;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.List;

public class Utils {
    public static final Function<Long, EventLog> PageWeight = new Function<Long, EventLog>(){
        public Long execute(EventLog eventLog) {
            List<ResponseDataLogElement> responseElements = eventLog.filtered(By.type(ResponseDataLogElement.class));

            long weight = 0;
            for (ResponseDataLogElement responseElement : responseElements) {
                weight += responseElement.responseWeight();
            }

            return weight;
        }
    } ;

    public static void close(Closeable item) {
        if (item != null) {
            try {
                item.close();
            } catch (IOException e) {
            }
        }
    }

    public static void close(Socket socket) {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
        }
    }

    public static void close(ServerSocket socket) {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
        }
    }

    public static String readLine(InputStream in) throws IOException {
        // reads a line of text from an InputStream
        StringBuilder data = new StringBuilder("");
        int c;

        // if we have nothing to read, just return null
        in.mark(1);
        if (in.read() == -1)
            return null;
        else
            in.reset();

        while ((c = in.read()) >= 0) {
            // check for an end-of-line character
            if ((c == 0) || (c == 10) || (c == 13))
                break;
            else
                data.append((char) c);
        }

        // deal with the case where the end-of-line terminator is \r\n
        if (c == 13) {
            in.mark(1);
            if (in.read() != 10)
                in.reset();
        }

        return data.toString();
    }
    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
