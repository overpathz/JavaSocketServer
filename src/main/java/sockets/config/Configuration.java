package sockets.config;

import java.net.InetAddress;
import java.net.UnknownHostException;

public final class Configuration {

    public static final int PORT = 8778;
    public static final String HOST = getHost();

    private static String getHost() {
        String hostAddress = null;
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
            return hostAddress;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }
}
