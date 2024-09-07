package org.bluesky.service;

import okhttp3.*;
import org.bluesky.BskyApiClient;
import org.bluesky.model.Post;
import org.bluesky.util.DateUtil;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class PostService {

    private final String token;
    private final BskyApiClient apiClientUrl;
    private final HttpClientService httpClientService;

    public PostService(String token) {
        this.token = token;
        this.apiClientUrl = BskyApiClient.getInstance();
        this.httpClientService = new HttpClientService();
    }

    public void createPost(String text, String handle) throws IOException {

        JSONObject postBody = new JSONObject();
        postBody.put("collection", "app.bsky.feed.post");
        postBody.put("repo", handle);

        JSONObject record = new JSONObject();
        record.put("text", text);
        record.put("createdAt", DateUtil.getCurrentISODate());
        record.put("$type", "app.bsky.feed.post");

        postBody.put("record", record);

        try (Response response = httpClientService.sendPostRequest(apiClientUrl.getPostFeedUrl(), postBody, token)) {
            if (!response.isSuccessful()) throw new IOException("Error in request: " + response.message());
        }
    }
}
