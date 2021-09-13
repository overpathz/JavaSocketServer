package sockets.server;

import javax.sql.rowset.serial.SerialStruct;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class UrlParser {

    public static String getRefererUrlFromHeaders(String stringHeaders) {

        String pattern = "Referer:(.+?)\\s*Accept";
        Pattern patternObj = Pattern.compile(pattern);
        Matcher matcher = patternObj.matcher(stringHeaders);

        String htmlUrl = null;

        while (matcher.find()) {
            htmlUrl = matcher.group(1);
        }

        htmlUrl = htmlUrl != null ? htmlUrl.strip() : null;

        return htmlUrl;
    }

    public static String getRefererUrlFromHeaders(byte[] byteHeaders) {
        String strResponse = new String(byteHeaders);
        return getRefererUrlFromHeaders(strResponse);
    }

    public static String getResourceFromUrl(String url) {
        String[] splitUrlBySlash = url.split("/");
        String resource = splitUrlBySlash[splitUrlBySlash.length-1].strip();
        return resource;
    }

    public static String getUrlAndResource(byte[] byteHeaders) {
        String url = getRefererUrlFromHeaders(byteHeaders);
        return getResourceFromUrl(url);
    }

}
