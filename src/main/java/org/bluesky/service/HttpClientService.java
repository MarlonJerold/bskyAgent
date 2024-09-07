package org.bluesky.service;

import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;

public class HttpClientService {

    private final OkHttpClient client;

    public HttpClientService() {
        this.client = new OkHttpClient.Builder().build();
    }

    public Response sendPostRequest(String url, JSONObject jsonBody, String token) throws IOException {
        RequestBody body = RequestBody.create(
                jsonBody.toString(),
                MediaType.parse("application/json; charset=utf-8")
        );

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Authorization", "Bearer " + token)
                .build();

        return client.newCall(request).execute();
    }

}
