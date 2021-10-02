package sockets.server.util;

import sockets.server.error.ResourceNotFoundError;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class UrlParser {

    public static String getRefererUrlFromHeaders(byte[] byteHeaders) {
        String stringHeaders = new String(byteHeaders);

        //String pattern = "Referer:(.+?)\\s*Accept";
        String pattern = "(^(GET) \\/?.+ )";
        Pattern patternObj = Pattern.compile(pattern);
        Matcher matcher = patternObj.matcher(stringHeaders);

        String htmlUrl = null;

        while (matcher.find()) {
            htmlUrl = matcher.group(1);
        }

        if (htmlUrl != null) {
            if (!htmlUrl.contains("favicon")) {
                String[] splitUrlBySlash = htmlUrl.strip().split("/");
                String resource = splitUrlBySlash[splitUrlBySlash.length-1].strip();

                return resource;
            }
        }

        throw new ResourceNotFoundError("resource not found");
    }
}
