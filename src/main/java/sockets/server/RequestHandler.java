package sockets.server;

import com.sun.net.httpserver.Headers;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

public class RequestHandler {
    public void processSocket(Socket socket) {
        try(
                socket;
                var inputStream = new BufferedInputStream(socket.getInputStream());
                var outputStream = new DataOutputStream(socket.getOutputStream());
        ) {

            byte[] bytes = inputStream.readNBytes(440);
            String strResponse = new String(bytes);

            System.out.println(strResponse);
            System.out.println(UrlParser.getRefererUrlFromHeaders(strResponse));

            var body = Files.readAllBytes(Path.of("src/main/resources/hello.html"));

            var headers = """
                    HTTP/1.1 200 OK
                    Accept: text/html
                    Content-Type: text/html
                    """.getBytes();

            outputStream.write(headers);
            outputStream.write(System.lineSeparator().getBytes());
            outputStream.write(body);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
