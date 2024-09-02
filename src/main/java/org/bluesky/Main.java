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
            Profile profile = blueskyClient.getProfile();

            System.out.println(profile.getDisplayName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}