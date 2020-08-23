package IO.NIO;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author 李高丞
 * @version 1.0
 */
public class NIOClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8888);
        OutputStream out = socket.getOutputStream();
        String s = "Hello, world";
        out.write(s.getBytes());
        out.close();
    }
}
