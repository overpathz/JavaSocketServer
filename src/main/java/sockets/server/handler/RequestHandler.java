package sockets.server;

import com.sun.net.httpserver.Headers;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class RequestHandler {

    public void processSocket(Socket socket) {
        try(
                socket;
               // var inputStream = new BufferedInputStream(socket.getInputStream());
                var inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                var outputStream = new DataOutputStream(socket.getOutputStream());
        ) {


            String inputLine;
            while (!(inputLine = inputStream.readLine()).equals(""))
                System.out.println(inputLine);

            System.out.println(inputLine);

            //byte[] bytes = inputStream.readNBytes(570);
            //String strResponse = new String(bytes);

            //System.out.println(strResponse);

            var body = Files.readAllBytes(Path.of("src/main/resources/hello.html"));

            var headers = """
                    HTTP/1.1 200 OK
                    Accept: text/html
                    Content-Type: text/html
                    """.getBytes();

            outputStream.write(headers);
            outputStream.write(System.lineSeparator().getBytes());
            outputStream.write(body);

            try {
                System.out.println("[REFERENCE] " + UrlParser.getRefererUrlFromHeaders(inputLine.getBytes()));
            } catch (NullPointerException e) {
                System.out.println("[SERVER] Request without referer header! Nothing to parse!");
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
