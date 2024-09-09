package org.bluesky.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.bluesky.BskyApiClient;
import org.bluesky.model.Profile;
import org.bluesky.service.PostService;
import org.bluesky.service.ProfileService;
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
    }

    public void createPost(String text) throws IOException {
        PostService postService = new PostService(token);
        postService.createPost(text, handle);
    }

    public Profile getProfile(String actor) throws IOException {
        ProfileService profileService = new ProfileService(token, actor);
        return profileService.getProfile();
    }

    public Response getPostThread(String atUri) throws IOException {
        PostService postService = new PostService(token);
        return postService.getPostThread(atUri);

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
}
