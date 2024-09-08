package org.bluesky.exception;

import java.io.IOException;

public class PostRequestException extends IOException {

    public PostRequestException(String message) {
        super(message);
    }

    public PostRequestException(String message, Throwable cause) {
        super(message, cause);
    }

}
