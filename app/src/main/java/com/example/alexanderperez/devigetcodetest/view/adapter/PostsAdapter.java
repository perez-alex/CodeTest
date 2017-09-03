package com.example.alexanderperez.devigetcodetest.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alexanderperez.devigetcodetest.R;
import com.example.alexanderperez.devigetcodetest.model.dto.Image;
import com.example.alexanderperez.devigetcodetest.model.dto.Post;
import com.example.alexanderperez.devigetcodetest.model.dto.PostData;
import com.example.alexanderperez.devigetcodetest.utils.BusProvider;
import com.example.alexanderperez.devigetcodetest.view.event.ImageClickedEvent;
import com.example.alexanderperez.devigetcodetest.view.event.NoImageClickEvent;
import com.squareup.otto.Bus;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by alexander.perez on 9/2/2017.
 */

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostViewHolder> {

    private List<PostData> posts;
    private Context context;

    public PostsAdapter(Context context, List<PostData> posts) {
        this.posts = new ArrayList<>(posts);
        this.context = context;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View postView = inflater.inflate(R.layout.item_post, parent, false);
        PostViewHolder viewHolder = new PostViewHolder(postView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        Post item = posts.get(position).getPost();
        holder.post = item;
        holder.title.setText(item.getTitle());
        int hours = (int) getTimeDifference(item.getCreated());
        String hoursAgo = context.getResources().getQuantityString(R.plurals.hours, hours, hours);
        String authorString = context.getString(R.string.date_and_author, hoursAgo, item.getAuthor());
        holder.author.setText(authorString);
        holder.comments.setText(context.getResources().getQuantityString(R.plurals.comments, item.getComments(), item.getComments()));
        holder.position.setText(context.getString(R.string.position, position + 1));
        if (item.getThumbnail().startsWith("http")) {
            Picasso.with(context)
                    .load(item.getThumbnail())
                    .into(holder.thumbnail);
        } else {
            holder.thumbnail.setImageResource(R.drawable.default_image);
        }
    }

    /**
     * Calculates the time difference between the current date and the post creation date
     *
     * @param timestamp creation date timestamp in seconds and UTC zone
     * @return difference in hours
     */
    private long getTimeDifference(long timestamp) {
        Calendar currentDate = Calendar.getInstance();
        long diff = currentDate.getTimeInMillis() / 1000 - timestamp;
        return TimeUnit.HOURS.convert(diff, TimeUnit.SECONDS);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void addPosts(List<PostData> posts) {
        this.posts.addAll(posts);
        notifyDataSetChanged();
    }

    class PostViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.thumbnail)
        ImageView thumbnail;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.author)
        TextView author;
        @BindView(R.id.comments)
        TextView comments;
        @BindView(R.id.position)
        TextView position;
        Post post;

        public PostViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.thumbnail)
        public void imageClicked() {
            Image image = post.getPreview().getImages().get(0);
            Bus bus = BusProvider.getInstance();
            ImageClickedEvent event = null;
            if (!image.hasVariants()) {
                event = new ImageClickedEvent(image.getSource().getUrl());
            } else {
                if (image.getVariants().getGif() != null) {
                    event = new ImageClickedEvent(image.getVariants().getGif().getSource().getUrl());
                } else if (image.getVariants().getMp4() != null) {
                    event = new ImageClickedEvent(image.getVariants().getMp4().getSource().getUrl());
                }
            }
            if (event != null) {
                bus.post(event);
            } else {
                bus.post(new NoImageClickEvent());
            }
        }
    }
}
