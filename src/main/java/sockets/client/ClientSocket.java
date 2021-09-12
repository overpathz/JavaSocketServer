package sockets.client;

import sockets.config.Configuration;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocket extends Thread {

    private Socket socket;
    public static final Scanner scanner = new Scanner(System.in);

    public ClientSocket(String host, int port) {
        try {
            this.socket = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        start();
    }

    public void sendMessage(String message) {
        try(var outputStream = new DataOutputStream(socket.getOutputStream());
            var inputStream = new DataInputStream(socket.getInputStream())) {

            outputStream.writeUTF(message);
        } catch (IOException e) {

        }
    }

    @Override
    public void run() {
        sendMessage("Nu tipa");
    }

    public static void main(String[] args) {

    }
}
