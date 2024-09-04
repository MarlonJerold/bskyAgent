package org.bluesky;

import org.bluesky.client.BskyAgent;
import org.bluesky.model.ThreadViewPost;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        try {
            String handle = "patinho.tech";
            String appPassword = "ua7a-hlcn-we4q-nfwc";

            BskyAgent blueskyClient = new BskyAgent(handle, appPassword);
            ThreadViewPost threadPost = blueskyClient.getPostThread("https://bsky.app/profile/patinho.tech/post/3l36ns7imat2o");
            System.out.println(threadPost);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}