package org.bluesky;

import org.bluesky.client.BskyAgent;
import org.bluesky.model.Post;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        try {
            String handle = "testedopato.bsky.social";
            String appPassword = "";

            BskyAgent agent = new BskyAgent(handle, appPassword);
            agent.createPost("Olá");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}