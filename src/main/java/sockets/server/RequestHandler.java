package sockets.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class RequestHandler {

    public void processSocket(Socket socket) {
        try(socket;
            var inputStream = new DataInputStream(socket.getInputStream());
            var outputStream = new DataOutputStream(socket.getOutputStream())) {

            // request handling
            System.out.println("Request: " + inputStream.readUTF());

            // response creating
            var body = "Hello from server!";

            outputStream.writeUTF(body);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
