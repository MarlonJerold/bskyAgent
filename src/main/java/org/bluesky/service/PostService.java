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

    private final OkHttpClient client;
    private final String token;
    private final ExecutorService executor;
    private final BskyApiClient apiClientUrl;

    public PostService(String token, int poolSize) {
        this.token = token;
        this.executor = Executors.newFixedThreadPool(poolSize);
        this.client = new OkHttpClient.Builder()
                .connectionPool(new ConnectionPool(20, 5, TimeUnit.MINUTES)) // Configuração de pool de conexões
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        this.apiClientUrl = BskyApiClient.getInstance();
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

    public void createPostsInParallel(List<Post> posts) {
        List<Future<?>> futures = new ArrayList<>();
        try {
            for (Post post : posts) {
                Future<?> future = executor.submit(() -> {
                    try {
                        createPost(post.getText(), post.getHandle());
                    } catch (IOException e) {
                        System.err.println("Failed to create post: " + e.getMessage());
                    }
                });
                futures.add(future);
            }
            for (Future<?> future : futures) {
                future.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Error executing posts in parallel", e);
        } finally {
            executor.shutdown();
        }
    }

    public void shutdown() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(2, TimeUnit.SECONDS)) {
                executor.shutdownNow();
                if (!executor.awaitTermination(2, TimeUnit.SECONDS)) {
                    System.err.println("Executor did not terminate.");
                }
            }
        } catch (InterruptedException ex) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
