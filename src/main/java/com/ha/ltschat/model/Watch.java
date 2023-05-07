package com.ha.ltschat.model;

public class Watch {
    private String userId;
    private String watchedId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWatchedId() {
        return watchedId;
    }

    public void setWatchedId(String watchedId) {
        this.watchedId = watchedId;
    }

    @Override
    public String toString() {
        return "Watch{" +
                "userId='" + userId + '\'' +
                ", watchedId='" + watchedId + '\'' +
                '}';
    }
}
