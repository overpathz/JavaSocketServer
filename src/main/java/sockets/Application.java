package sockets;

import sockets.config.Configuration;
import sockets.server.HostServer;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {
    public static void main(String[] args) {
        new HostServer(Configuration.PORT, 100).run();
    }
}