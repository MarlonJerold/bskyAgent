package org.bluesky.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Response;
import org.bluesky.BskyApiClient;
import org.bluesky.model.Profile;

import java.io.IOException;

public class ProfileService {
    private final String token;
    private final BskyApiClient apiClientUrl;
    private final HttpClientService httpClientService;
    private final String actor;

    public ProfileService(String token, String actor) {
        this.token = token;
        this.actor = actor;
        this.apiClientUrl = BskyApiClient.getInstance();
        this.httpClientService = new HttpClientService();
    }

    public Profile getProfile() throws IOException {
        try (Response response = httpClientService.getProfileRequest(apiClientUrl.getGetProfileUrl(), token, actor)) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                ObjectMapper objectMapper = new ObjectMapper();
                Profile profile = objectMapper.readValue(responseBody, Profile.class);
                return profile;
            } else {
                throw new IOException("Failed to get profile. Status: " + response.code());
            }
        }
    }
}
