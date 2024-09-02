package org.bluesky;

import org.bluesky.client.BskyAgent;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        try {
            String handle = "seu @";
            String appPassword = "password que o app oferece";
            BskyAgent blueskyClient = new BskyAgent(handle, appPassword);
            blueskyClient.createPost("ultimo teste :/");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}