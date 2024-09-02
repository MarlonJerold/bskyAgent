package org.bluesky.client;


import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.bluesky.model.Profile;
import org.bluesky.util.DateUtil;
import org.json.JSONObject;

import java.io.IOException;

public class BskyAgent {

    private static final String DID_URL = "https://bsky.social/xrpc/com.atproto.identity.resolveHandle";
    private static final String API_KEY_URL = "https://bsky.social/xrpc/com.atproto.server.createSession";
    private static final String POST_FEED_URL = "https://bsky.social/xrpc/com.atproto.repo.createRecord";
    private static final String GET_PROFILE_URL = "https://public.api.bsky.app/xrpc/app.bsky.actor.getProfile";

    private final OkHttpClient client = new OkHttpClient();
    private String token;

    public BskyAgent(String handle, String appPassword) throws IOException {
        String did = resolveHandle(handle);
        this.token = authenticate(did, appPassword);
    }

    private String resolveHandle(String handle) throws IOException {
        HttpUrl url = HttpUrl.parse(DID_URL).newBuilder()
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
                .url(API_KEY_URL)
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
                .url(POST_FEED_URL)
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

    public Profile getProfile() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        String urlWithParams = HttpUrl.parse(GET_PROFILE_URL)
                .newBuilder()
                .addQueryParameter("actor", "patinho.tech")
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
}
