package sockets.server.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class UrlParser {

    public static String getRefererUrlFromHeaders(byte[] byteHeaders) {
        String stringHeaders = new String(byteHeaders);

        String pattern = "Referer:(.+?)\\s*Accept";
        Pattern patternObj = Pattern.compile(pattern);
        Matcher matcher = patternObj.matcher(stringHeaders);

        String htmlUrl = null;

        while (matcher.find()) {
            htmlUrl = matcher.group(1);
        }

        htmlUrl = htmlUrl != null ? htmlUrl.strip() : null;

        String[] splitUrlBySlash = htmlUrl.split("/");
        String resource = splitUrlBySlash[splitUrlBySlash.length-1].strip();

        return resource;
    }
}
