package com.example.alexanderperez.devigetcodetest.presenter;

import com.example.alexanderperez.devigetcodetest.model.RedditModel;
import com.example.alexanderperez.devigetcodetest.model.dto.PostData;
import com.example.alexanderperez.devigetcodetest.model.event.ListingFailureEvent;
import com.example.alexanderperez.devigetcodetest.model.event.ListingSuccessEvent;
import com.example.alexanderperez.devigetcodetest.view.RedditView;
import com.example.alexanderperez.devigetcodetest.view.activity.RedditActivity;
import com.example.alexanderperez.devigetcodetest.view.event.ImageClickedEvent;
import com.example.alexanderperez.devigetcodetest.view.event.LoadMorePostsEvent;
import com.example.alexanderperez.devigetcodetest.view.event.NoImageClickEvent;
import com.squareup.otto.Subscribe;

import java.util.List;

import static com.example.alexanderperez.devigetcodetest.model.DefaultValues.MAX_POSTS;

/**
 * Created by alexander.perez on 9/2/2017.
 */

public class RedditPresenter implements Presenter<RedditActivity> {

    private RedditModel model;
    private RedditView view;

    public RedditPresenter() {

    }

    @Subscribe
    public void listingSuccessEvent(ListingSuccessEvent event) {
        List<PostData> posts = event.getListing().getData().getPosts();
        view.setPostsList(posts);
        model.savePost(posts);
        model.setAfter(event.getListing().getData().getAfter());
        view.hideProgressBar();
    }

    @Subscribe
    public void listingFailureEvent(ListingFailureEvent event) {
        view.hideProgressBar();
        view.shotToastWithError();
    }

    @Subscribe
    public void imageClickedEvent(ImageClickedEvent event) {
        view.openImage(event.getImageUrl());
    }

    @Subscribe
    public void noImageClickEvent(NoImageClickEvent event) {
        view.showNoImageToast();
    }

    public void fetchNextPage() {
        model.getTopPosts(model.getAfter());
        view.showProgressBar();
    }

    @Subscribe
    public void loadMorePostsEvent(LoadMorePostsEvent event) {
        if (model.getItemCount() < MAX_POSTS) {
            fetchNextPage();
        }
    }

    @Override
    public void onViewAttached(RedditActivity activity) {
        //initialize view only if rotating or starting app
        if (this.view == null || this.view.getActivity() != activity) {
            this.view = new RedditView(activity);
            if (model.getItemCount() == 0) {
                //creating from scratch
                fetchNextPage();
            } else {
                //recreating activity
                view.setPostsList(model.getCurrentPosts());
                view.hideProgressBar();
            }
        }
    }

    @Override
    public void onViewDetached() {

    }

    @Override
    public void onDestroyed() {

    }

    public void setModel(RedditModel model) {
        this.model = model;
    }
}
