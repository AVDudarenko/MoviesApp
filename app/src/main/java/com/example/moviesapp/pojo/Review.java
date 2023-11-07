package com.example.moviesapp.pojo;

import com.google.gson.annotations.SerializedName;

public class Review {

    @SerializedName("author")
    private String author;
    @SerializedName("review")
    private String review;
    @SerializedName("type")
    private String type;
    @SerializedName("title")
    private String title;

    public Review(String author, String review, String type, String title) {
        this.author = author;
        this.review = review;
        this.type = type;
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public String getReview() {
        return review;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Review{" +
                "author='" + author + '\'' +
                ", review='" + review + '\'' +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
