package com.hodgepodge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mahmutsamiyagmur on 17.12.2015.
 */
public class TopicAdapter extends ArrayAdapter<String> {
    public ListView listView;
    private Context context;

    public TopicAdapter(Context context, int resource,  ArrayList<String> items ) {
        super(context, resource,items);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = (convertView == null) ? inflater.inflate(R.layout.simple_list_item_1,parent,false) : convertView;

        TextView myText = (TextView)v.findViewById(R.id.SmallListItem);

        myText.setText(getItem(position));

        return v;
    }
}
