package org.bluesky.model;

public class Post {
    private final String text;
    private final String handle;

    public Post(String text, String handle) {
        this.text = text;
        this.handle = handle;
    }

    public Post(String text) {
        this(text, null);
    }

    public String getText() {
        return text;
    }

    public String getHandle() {
        return handle;
    }
}
