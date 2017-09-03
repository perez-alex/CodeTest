package com.example.alexanderperez.devigetcodetest.model.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by alexander.perez on 9/2/2017.
 */

public class Post {

    String title;
    String author;
    @SerializedName("created_utc")
    long created;
    String thumbnail;
    @SerializedName("num_comments")
    int comments;
    PostPreview preview;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public long getCreated() {
        return created;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public int getComments() {
        return comments;
    }

    public PostPreview getPreview() {
        return preview;
    }
}
