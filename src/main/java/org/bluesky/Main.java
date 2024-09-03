package org.bluesky;

import org.bluesky.client.BskyAgent;
import org.bluesky.model.Profile;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        try {
            String handle = "seu @";
            String appPassword = "sua senha";

            BskyAgent blueskyClient = new BskyAgent(handle, appPassword);
            blueskyClient.getPostThread("https://bsky.app/profile/patinho.tech/post/3l3bfzeuikf2h");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}