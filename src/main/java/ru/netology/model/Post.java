package ru.netology.model;

public class Post {
    private long id;
    private String content;
    private boolean isReadyToRemoved;

    public Post() {
    }

    public Post(long id, String content) {
        this.id = id;
        this.content = content;
        this.isReadyToRemoved = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isReadyToRemoved() {
        return isReadyToRemoved;
    }

    public void setReadyToRemoved(boolean readyToRemoved) {
        isReadyToRemoved = readyToRemoved;
    }
}
