package org.bluesky.service;

import okhttp3.*;
import org.bluesky.BskyApiClient;
import org.bluesky.exception.PostRequestException;
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
        JSONObject postBody = JsonBuilderService.createPostJson(text, handle);
        try (Response response = httpClientService.sendPostRequest(apiClientUrl.getPostFeedUrl(), postBody, token)) {
            if (!response.isSuccessful()) {
                throw new PostRequestException("Failed to create post. Status: " + response.code() + ", Message: " + response.message());
            }
        } catch (IOException e) {
            throw new PostRequestException("Network error occurred while creating post", e);
        }
    }
}
