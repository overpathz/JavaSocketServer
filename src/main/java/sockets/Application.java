package sockets;

import sockets.config.Configuration;
import sockets.server.HostServer;

public class Application {
    public static void main(String[] args) {
        new HostServer(Configuration.PORT, 100).run();
    }
}