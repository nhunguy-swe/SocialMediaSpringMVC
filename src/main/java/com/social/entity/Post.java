package com.social.entity;

import java.sql.Timestamp;

public class Post {

    private int id;
    private String title;
    private String body;
    private int userId;
    private String status;
    private Timestamp createdAt;
    private String authorName;

    public Post() {
    }

    public Post(int id, String title, String body, int userId, String status, Timestamp createdAt, String authorName) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.userId = userId;
        this.status = status;
        this.createdAt = createdAt;
        this.authorName = authorName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
