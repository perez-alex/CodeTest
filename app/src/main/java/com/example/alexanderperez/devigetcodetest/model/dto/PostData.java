package com.example.alexanderperez.devigetcodetest.model.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by alexander.perez on 9/2/2017.
 */

public class PostData {
    @SerializedName("data")
    Post post;

    public Post getPost() {
        return post;
    }
}
