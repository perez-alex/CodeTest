package com.example.alexanderperez.devigetcodetest.model;

import com.example.alexanderperez.devigetcodetest.model.dto.Listing;
import com.example.alexanderperez.devigetcodetest.model.dto.PostData;
import com.example.alexanderperez.devigetcodetest.model.event.ListingFailureEvent;
import com.example.alexanderperez.devigetcodetest.model.event.ListingSuccessEvent;
import com.example.alexanderperez.devigetcodetest.services.RedditService;
import com.example.alexanderperez.devigetcodetest.services.Service;
import com.squareup.otto.Bus;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alexander.perez on 9/2/2017.
 */

public class RedditModel {

    private final Bus bus;
    private String after;
    private List<PostData> posts;

    public RedditModel(Bus bus) {
        this.bus = bus;
    }

    public void getTopPosts(String after) {
        RedditService redditService = Service.getRedditService();
        Call<Listing> call = redditService.getTopPosts(DefaultValues.TOP_TIME, DefaultValues.PAGE_LIMIT, after);
        call.enqueue(new Callback<Listing>() {
            @Override
            public void onResponse(Call<Listing> call, Response<Listing> response) {
                bus.post(new ListingSuccessEvent(response.body()));
            }

            @Override
            public void onFailure(Call<Listing> call, Throwable t) {
                bus.post(new ListingFailureEvent());
            }
        });
    }

    public void setAfter(String after) {
        this.after = after;
    }

    public String getAfter() {
        return after;
    }

    public int getItemCount() {
        if (posts == null) {
            return 0;
        }
        return posts.size();
    }

    public void savePost(List<PostData> postsList) {
        if (posts == null) {
            posts = new ArrayList<>(postsList);
        } else {
            posts.addAll(postsList);
        }
    }

    public List<PostData> getCurrentPosts() {
        return posts;
    }
}
