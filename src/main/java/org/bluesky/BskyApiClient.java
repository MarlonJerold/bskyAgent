package org.bluesky;

public class BskyApiClient {

    private static BskyApiClient instance;

    private static final String DID_URL = "https://bsky.social/xrpc/com.atproto.identity.resolveHandle";
    private static final String API_KEY_URL = "https://bsky.social/xrpc/com.atproto.server.createSession";
    private static final String POST_FEED_URL = "https://bsky.social/xrpc/com.atproto.repo.createRecord";
    private static final String GET_PROFILE_URL = "https://public.api.bsky.app/xrpc/app.bsky.actor.getProfile";

    private BskyApiClient() {
    }

    public static BskyApiClient getInstance() {
        if (instance == null) {
            synchronized (BskyApiClient.class) {
                if (instance == null) {
                    instance = new BskyApiClient();
                }
            }
        }
        return instance;
    }

    public String getDidUrl() {
        return DID_URL;
    }

    public String getApiKeyUrl() {
        return API_KEY_URL;
    }

    public String getPostFeedUrl() {
        return POST_FEED_URL;
    }

    public String getGetProfileUrl() {
        return GET_PROFILE_URL;
    }
}
