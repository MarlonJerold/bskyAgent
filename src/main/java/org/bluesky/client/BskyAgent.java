package org.bluesky.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.bluesky.BskyApiClient;
import org.bluesky.model.Profile;
import org.bluesky.util.DateUtil;
import org.json.JSONObject;

import java.io.IOException;

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

    public void createPost(String text) throws IOException {
        JSONObject postBody = new JSONObject();
        postBody.put("collection", "app.bsky.feed.post");
        postBody.put("repo", "patinho.tech");

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
                .addHeader("Authorization","Bearer " + token )
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

    public void getPostThread(String atUri) throws IOException {

        String did = DateUtil.extractDid(atUri);
        Profile profile = getProfile(did);

        HttpUrl urlRequest = HttpUrl.parse(apiClientUrl.getGetPostThreadUrl()).newBuilder()
                .addQueryParameter("uri", DateUtil.formatString(profile.getDid(), DateUtil.extractPostId(atUri)))
                .build();

        Request request = new Request.Builder()
                .url(urlRequest)
                .get()
                .addHeader("Accept", "application/json")
                .addHeader("Authorization","Bearer " + token )
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                System.out.println(responseBody);
            } else {
                System.out.println("Erro: " + response.code() + " - " + response.message());
                if (response.body() != null) {
                    System.out.println("Corpo do Erro: " + response.body().string());
                }
            }
        }
    }

}
