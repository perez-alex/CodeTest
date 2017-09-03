package com.example.alexanderperez.devigetcodetest.services;

import com.example.alexanderperez.devigetcodetest.model.dto.Listing;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by alexander.perez on 8/31/2017.
 */

public interface RedditService {

    @GET("top.json")
    Call<Listing> getTopPosts(@Query("t") String time, @Query("limit") int limit,
                              @Query("after") String after);
}
