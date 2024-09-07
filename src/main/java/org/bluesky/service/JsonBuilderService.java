package org.bluesky.service;

import org.bluesky.util.DateUtil;
import org.json.JSONObject;

public class JsonBuilderService {

    public static JSONObject createPostJson(String text, String handle) {
        JSONObject postBody = new JSONObject();
        postBody.put("collection", "app.bsky.feed.post");
        postBody.put("repo", handle);

        JSONObject record = new JSONObject();
        record.put("text", text);
        record.put("createdAt", DateUtil.getCurrentISODate());
        record.put("$type", "app.bsky.feed.post");

        postBody.put("record", record);

        return postBody;
    }
}
