package com.hodgepodge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by apple on 11/12/15.
 */
public class PostsListAdapter extends BaseAdapter {

    ArrayList <PostListData> posts = new ArrayList<PostListData>();
    LayoutInflater inflater;
    Context context;


    public PostsListAdapter (Context context, ArrayList<PostListData> posts) {
        this.posts = posts;
        this.context = context;
        inflater = LayoutInflater.from(this.context);

    }

    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public PostListData getItem(int position) {
        return posts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PostViewHolder postViewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_posts_list_item, null);
            postViewHolder = new PostViewHolder();
            convertView.setTag(postViewHolder);

        } else {
            postViewHolder = (PostViewHolder)convertView.getTag();
        }
        postViewHolder.contextTextView = (TextView) convertView.findViewById(R.id.contentTextView);
        postViewHolder.contextTextView.setText(posts.get(position).getContent());
        postViewHolder.dateTextView = (TextView) convertView.findViewById(R.id.dateTextView);
        postViewHolder.dateTextView.setText(posts.get(position).getDate());
        postViewHolder.ratingTextView = (TextView) convertView.findViewById(R.id.postRatingTextView);
        Integer rating = posts.get(position).getRating();
        String rS = rating.toString();
        postViewHolder.ratingTextView.setText(rS);
        postViewHolder.upVoteButton = (ImageButton) convertView.findViewById(R.id.upVoteButton);
        postViewHolder.downVoteButton = (ImageButton) convertView.findViewById(R.id.downVoteButton);
        postViewHolder.usernameTextView = (TextView) convertView.findViewById(R.id.usernameTextView);
        postViewHolder.usernameTextView.setText(posts.get(position).getUsername());
        return convertView;
    }


    private class PostViewHolder {
        TextView contextTextView;
        TextView dateTextView;
        ImageButton upVoteButton;
        ImageButton downVoteButton;
        TextView ratingTextView;
        TextView usernameTextView;

    }
}
