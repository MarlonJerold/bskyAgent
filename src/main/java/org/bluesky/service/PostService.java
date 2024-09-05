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

    public PostService(String token) {
        this.token = token;
        this.apiClientUrl = BskyApiClient.getInstance();
    }

    public void createPost(String text, String handle) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        JSONObject postBody = new JSONObject();
        postBody.put("collection", "app.bsky.feed.post");
        postBody.put("repo", handle);

        JSONObject record = new JSONObject();
        record.put("text", text);
        record.put("createdAt", DateUtil.getCurrentISODate());
        record.put("$type", "app.bsky.feed.post");

        postBody.put("record", record);

        RequestBody body = RequestBody.create(
                postBody.toString(),
                MediaType.parse("application/json; charset=utf-8")
        );

        Request request = new Request.Builder()
                .url(apiClientUrl.getPostFeedUrl())
                .post(body)
                .addHeader("Authorization", "Bearer " + token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Error in request: " + response.message());
        }
    }
}
