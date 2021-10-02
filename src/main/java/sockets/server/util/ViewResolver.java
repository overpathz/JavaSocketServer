package sockets.server.util;

import sockets.config.MvcConfig;
import sockets.server.error.PageNotFoundError;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class ViewResolver {

    private static final String suffix;
    private static final String prefix;

    static {
        prefix = MvcConfig.PREFIX;
        suffix = MvcConfig.SUFFIX;
    }

    public static byte[] resolveView(String refer) {

        String expectedPath = "src/main" + prefix;
        String expectedFile = expectedPath + "/" + refer + suffix;

        if (Files.exists(Path.of(expectedPath))) {
            byte[] view;

            try {
                view = Files.readAllBytes(Path.of(expectedFile));
                return view;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        throw new PageNotFoundError("page not found");

    }
}
