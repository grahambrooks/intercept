package intercept.utils;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class UtilsUnitTests {
    @Test
    public void nullCloseableItemsAreIgnored() {
        Utils.close((Closeable) null);
        Utils.close((Socket) null);
    }

    @Test
    public void validObjectsAreClosed() throws IOException {
        Closeable c = mock(Closeable.class);
        Utils.close(c);
        verify(c).close();

        Socket s = mock(Socket.class);
        Utils.close(s);
        verify(s).close();
    }

    @Test
    public void ioExceptionsAreIgnored() throws IOException {
        Closeable c = mock(Closeable.class);
        doThrow(new IOException()).when(c).close();
        Utils.close(c);
        verify(c).close();

        Socket s = mock(Socket.class);
        doThrow(new IOException()).when(s).close();
        Utils.close(s);
        verify(s).close();
    }

    @Test
    public void readLineHandlesSimpleText() throws IOException {
        String expected = "\nThis is" +
                "\r\nSome lines of text" +
                "\r\n";
        InputStream input = new ByteArrayInputStream(expected.getBytes());

        assertThat(Utils.readLine(input), is(""));
        assertThat(Utils.readLine(input), is("This is"));
        assertThat(Utils.readLine(input), is("Some lines of text"));
        assertThat(Utils.readLine(input), is((String) null));
    }
}
