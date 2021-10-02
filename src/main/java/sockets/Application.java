package sockets;

import sockets.config.Configuration;
import sockets.server.HostServer;

public class Application {

    public static void main(String[] args) throws NoSuchFieldException {
        new HostServer(Configuration.PORT, Configuration.POOL_SIZE).run();
    }
}