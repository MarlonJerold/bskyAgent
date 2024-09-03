package org.bluesky.util;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static String getCurrentISODate() {
        return DateTimeFormatter.ISO_INSTANT.format(Instant.now().atZone(ZoneOffset.UTC));
    }

    public static String extractDid(String uri) {

        if (uri.startsWith("https://bsky.app/profile/")) {
            String profilePart = uri.substring("https://bsky.app/profile/".length());

            int postIndex = profilePart.indexOf("/post/");
            if (postIndex != -1) {
                return profilePart.substring(0, postIndex);
            } else {
                return profilePart;
            }
        }
        return null;
    }

    public static String formatString(String did, String postId) {
        String baseUrl = "at://%s/app.bsky.feed.post/%s";
        return String.format(baseUrl, did, postId);
    }

    public static String extractPostId(String url) {

        if (url.startsWith("https://bsky.app/profile/") && url.contains("/post/")) {
            String postPart = url.substring(url.indexOf("/post/") + 6);
            return postPart;
        }
        return null;
    }

}
