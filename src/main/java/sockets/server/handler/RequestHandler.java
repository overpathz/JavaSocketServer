package sockets.server.handler;

import sockets.server.util.UrlParser;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

public class RequestHandler {

    public void processSocket(Socket socket) {
        try(
                socket;
                var inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                var outputStream = new DataOutputStream(socket.getOutputStream());
        ) {

            StringBuilder request = new StringBuilder();
            String inputLine;
            while (!(inputLine = inputStream.readLine()).equals("")) {
                request.append(inputLine);
            }

            var body = Files.readAllBytes(Path.of("src/main/resources/hello.html"));

            var headers = """
                    HTTP/1.1 200 OK
                    Accept: text/html
                    Content-Type: text/html
                    """.getBytes();

            try {
                System.out.println("[REFERENCE] " + UrlParser.getRefererUrlFromHeaders(request.toString().getBytes()));
            } catch (NullPointerException e) {
                System.out.println("[SERVER] Request without referer header! Nothing to parse!");
            }

            outputStream.write(headers);
            outputStream.write(System.lineSeparator().getBytes());
            outputStream.write(body);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
