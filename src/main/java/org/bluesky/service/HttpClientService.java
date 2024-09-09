package org.bluesky.service;

import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
        return client.newCall(buildPostRequest(url,body,token )).execute();
    }

    public Response getProfileRequest(String url, String token, String actor) throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("actor", actor);

        String urlWithParams = buildUrlWithParams(url, params);

        return client.newCall(buildGetRequest(urlWithParams, token)).execute();
    }

    public Response buildGetResponse(String url, String token) throws IOException {
        return client.newCall(buildGetRequest(url, token)).execute();
    }

    public Request buildGetRequest(String url, String token) {
        return new Request.Builder()
                .url(url)
                .get()
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "Bearer " + token)
                .build();
    }

    private Request buildPostRequest(String url, RequestBody body, String token) {
        return new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Authorization", "Bearer " + token)
                .build();
    }

    private static String buildUrlWithParams(String baseUrl, Map<String, String> queryParams) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl).newBuilder();

        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
        }
        return urlBuilder.build().toString();
    }
}
