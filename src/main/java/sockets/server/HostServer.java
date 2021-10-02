package sockets.server;

import sockets.server.handler.RequestHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HostServer {

    private boolean stopped = false;
    private final ExecutorService executorService;
    private final int port;
    private final RequestHandler requestHandler;

    public HostServer(int port, int poolSize) {
        this.port = port;
        this.executorService = Executors.newFixedThreadPool(poolSize);
        this.requestHandler = new RequestHandler();
    }

    public void run() {
        try {
            var server = new ServerSocket(port);
            while (!stopped) {
                var socket = server.accept();
                executorService.execute(()->processSocket(socket));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void stopServer() {
        this.stopped = true;
    }

    private void processSocket(Socket socket) {
        requestHandler.processSocket(socket);
    }
}