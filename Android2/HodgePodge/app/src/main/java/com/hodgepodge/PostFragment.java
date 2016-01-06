package com.hodgepodge;

/**
 * Created by mahmutsamiyagmur on 15.12.2015.
 */
/*public class PostFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.post_fragment_layout, container, false);
    }
}*/

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by mahmutsamiyagmur on 15.12.2015.
 */

public class PostFragment extends Fragment {
    public ListView listView;

    /// SERVICE ///
    String postsString;
    ArrayList<String> Posts = new ArrayList<String>();

    public JSONArray posts;
    public TextView textUsername;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.topic_fragment_layout, container, false);
        return v;
    }
}