package com.hodgepodge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

        return convertView;
    }

    private static  class PostViewHolder {
        TextView idTextView;
        TextView contextTextView;
    }
}
