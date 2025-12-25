package com.actors.handler;

import java.time.LocalDateTime;

public class Err {
    private LocalDateTime timeStamp;
    private int status;
    private String error;
    private String message;
    private String path;

    public Err(LocalDateTime timeStamp, int status, String error, String message, String path) {
        this.timeStamp = timeStamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public LocalDateTime getTimeStamp() { return timeStamp; }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }
}
