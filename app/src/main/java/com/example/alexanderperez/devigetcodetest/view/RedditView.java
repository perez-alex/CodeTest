package com.example.alexanderperez.devigetcodetest.view;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.alexanderperez.devigetcodetest.R;
import com.example.alexanderperez.devigetcodetest.model.dto.PostData;
import com.example.alexanderperez.devigetcodetest.utils.BusProvider;
import com.example.alexanderperez.devigetcodetest.utils.EndlessRecyclerViewScrollListener;
import com.example.alexanderperez.devigetcodetest.view.adapter.PostsAdapter;
import com.example.alexanderperez.devigetcodetest.view.event.LoadMorePostsEvent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alexander.perez on 9/2/2017.
 */

public class RedditView extends ActivityView {

    @BindView(R.id.posts_list)
    RecyclerView postsList;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    private EndlessRecyclerViewScrollListener scrollListener;
    private PostsAdapter adapter;

    public RedditView(AppCompatActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(getContext().getDrawable(R.drawable.post_divider));
        postsList.addItemDecoration(itemDecoration);

        scrollListener = new EndlessRecyclerViewScrollListener((LinearLayoutManager) postsList.getLayoutManager()) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                BusProvider.getInstance().post(new LoadMorePostsEvent());
            }
        };
        postsList.addOnScrollListener(scrollListener);
    }

    public void setPostsList(List<PostData> posts) {
        if (adapter == null) {
            adapter = new PostsAdapter(getContext(), posts);
            postsList.setAdapter(adapter);
        } else {
            adapter.addPosts(posts);
        }
    }

    public void openImage(String imageUrl) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(imageUrl));
        getActivity().startActivity(browserIntent);
    }

    public void showNoImageToast() {
        Toast.makeText(getContext(), R.string.no_image_available, Toast.LENGTH_LONG).show();
    }

    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    public void shotToastWithError() {
        Toast.makeText(getContext(), R.string.error_while_loading, Toast.LENGTH_LONG).show();
    }
}
