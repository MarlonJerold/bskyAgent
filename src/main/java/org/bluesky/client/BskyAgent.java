package org.bluesky.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.bluesky.BskyApiClient;
import org.bluesky.model.Profile;
import org.bluesky.model.ThreadViewPost;
import org.bluesky.util.DateUtil;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BskyAgent {

    BskyApiClient apiClientUrl = BskyApiClient.getInstance();

    private final OkHttpClient client = new OkHttpClient();
    private String token;
    public String handle;

    public BskyAgent(String handle, String appPassword) throws IOException {
        String did = resolveHandle(handle);
        this.handle = handle;
        this.token = authenticate(did, appPassword);
        System.out.println(token);
    }

    private String resolveHandle(String handle) throws IOException {
        HttpUrl url = HttpUrl.parse(apiClientUrl.getDidUrl()).newBuilder()
                .addQueryParameter("handle", handle)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                JSONObject json = new JSONObject(responseBody);
                return json.getString("did");
            } else {
                throw new IOException("Unexpected code " + response);
            }
        }
    }

    private String authenticate(String did, String appPassword) throws IOException {
        JSONObject json = new JSONObject();
        json.put("identifier", did);
        json.put("password", appPassword);

        RequestBody body = RequestBody.create(
                json.toString(),
                MediaType.parse("application/json; charset=utf-8")
        );

        Request request = new Request.Builder()
                .url(apiClientUrl.getApiKeyUrl())
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                JSONObject jsonResponse = new JSONObject(responseBody);
                return jsonResponse.getString("accessJwt");
            } else {
                throw new IOException("Unexpected code " + response);
            }
        }
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
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                System.out.println("Response: " + responseBody);
            } else {
                throw new IOException("Error in request: " + response.message());
            }
        }
    }

    public Profile getProfile(String actor) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        String urlWithParams = HttpUrl.parse(apiClientUrl.getGetProfileUrl())
                .newBuilder()
                .addQueryParameter("actor", actor)
                .build()
                .toString();

        Request request = new Request.Builder()
                .url(urlWithParams)
                .get()
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "Bearer " + token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();

                ObjectMapper objectMapper = new ObjectMapper();
                Profile profile = objectMapper.readValue(responseBody, Profile.class);
                return profile;
            } else {
                System.out.println("Erro: " + response.code() + " - " + response.message());
                if (response.body() != null) {
                    System.out.println("Corpo do Erro: " + response.body().string());
                }
                return null;
            }
        }
    }

    public ThreadViewPost getPostThread(String atUri) throws IOException {

        String did = DateUtil.extractDid(atUri);
        Profile profile = getProfile(did);

        HttpUrl urlRequest = HttpUrl.parse(apiClientUrl.getGetPostThreadUrl()).newBuilder()
                .addQueryParameter("uri", DateUtil.formatString(profile.getDid(), DateUtil.extractPostId(atUri)))
                .build();

        Request request = new Request.Builder()
                .url(urlRequest)
                .get()
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "Bearer " + token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                ObjectMapper objectMapper = new ObjectMapper();

                ThreadViewPost threadViewPost = objectMapper.readValue(responseBody, ThreadViewPost.class);

                // Acessar o post e a thread
                ThreadViewPost.Thread thread = threadViewPost.getThread();
                ThreadViewPost.Post post = thread.getPost();
                List<ThreadViewPost> replies = thread.getReplies();

                // Exibir detalhes do post
                System.out.println("Post URI: " + post.getUri());
                System.out.println("Post CID: " + post.getCid());
                System.out.println("Post Reply Count: " + post.getReplyCount());
                System.out.println("Post Repost Count: " + post.getRepostCount());
                System.out.println("Post Like Count: " + post.getLikeCount());
                System.out.println("Post Quote Count: " + post.getQuoteCount());
                System.out.println("Post Indexed At: " + post.getIndexedAt());

                System.out.println("Comments in the thread:");
                printReplies(replies, objectMapper);

                return threadViewPost;
            } else {
                System.out.println("Erro: " + response.code() + " - " + response.message());
                if (response.body() != null) {
                    System.out.println("Corpo do Erro: " + response.body().string());
                }
            }
        }
        return null;
    }


    private static void printReplies(List<ThreadViewPost> replies, ObjectMapper objectMapper) {
        if (replies != null) {
            for (ThreadViewPost replyThread : replies) {
                ThreadViewPost.Post replyPost = replyThread.getThread().getPost();
                System.out.println("  - Reply Post URI: " + replyPost.getUri());
                System.out.println("    Reply Post CID: " + replyPost.getCid());
                System.out.println("    Reply Post Text: " + replyPost.getRecord().getText());
                // Recursivamente imprimir replies se houver
                printReplies(replyThread.getThread().getReplies(), objectMapper);
            }
        }
    }

}
