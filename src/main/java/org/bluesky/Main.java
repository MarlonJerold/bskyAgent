package org.bluesky;

import org.bluesky.client.BskyAgent;
import org.bluesky.model.Profile;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        try {

            BskyAgent agent = new BskyAgent("testedopato.bsky.social","bobinho");
            Profile profile = agent.getProfile("testedopato.bsky.social");
            System.out.println(profile.getFollowersCount());
            agent.createPost("Hello World");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}