package sockets.server.handler;

import sockets.server.error.PageNotFoundError;
import sockets.server.util.UrlParser;
import sockets.server.util.ViewResolver;

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
                request.append(inputLine).append("\n");
            }

            var headers = """
                    HTTP/1.1 200 OK
                    Accept: text/html
                    Content-Type: text/html
                    """.getBytes();

            System.out.println(request.toString());

            try {
                String parsedView = UrlParser.getRefererUrlFromHeaders(request.toString().getBytes());

                System.out.println(parsedView);

                var view = ViewResolver.resolveView(parsedView);

                outputStream.write(headers);
                outputStream.write(System.lineSeparator().getBytes());
                outputStream.write(view);

            } catch (PageNotFoundError e) {

                System.out.println("Словили");
                var notFoundPage = Files.readAllBytes(Path.of("src/main/resources/errorView/404.html"));
                outputStream.write(headers);
                outputStream.write(System.lineSeparator().getBytes());
                outputStream.write(notFoundPage);

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
