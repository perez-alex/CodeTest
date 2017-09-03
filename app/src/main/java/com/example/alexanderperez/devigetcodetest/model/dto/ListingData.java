package com.example.alexanderperez.devigetcodetest.model.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by alexander.perez on 9/2/2017.
 */

public class ListingData {
    String before;
    String after;
    @SerializedName("children")
    List<PostData> posts;

    public String getBefore() {
        return before;
    }

    public String getAfter() {
        return after;
    }

    public List<PostData> getPosts() {
        return posts;
    }
}
