package com.hodgepodge;

/**
 * Created by mahmutsamiyagmur on 15.12.2015.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class PostFragment extends Fragment {
    public ListView listViewP;

    ArrayList<String> Posts = new ArrayList<String>();

    public  JSONArray posts;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.post_fragment_layout, container, false);

        //Taking the user name by the shared preferences
        //SharedPreferences user = getSharedPreferences("UserInfo", 0);
        String id="";
        //+user.getString("ID",id)
        Bundle b = new Bundle();
        String userid = b.getString("userid");
        //////////// SERVICE //////////////
        ServiceClient.get("user/id/"+userid, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                try {

                    JSONObject resultArray = (JSONObject) response.get("result");
                    if (resultArray.get("result").equals("OK")) {
                        String username = response.getString("username");

                        //textUsername = (TextView) findViewById(R.id.textUserName);
                        posts = (JSONArray) response.get("createdPosts");

                        // IN this part we get the titles of each topic

                        for (int i = 0; i< posts.length(); i++){
                            JSONObject postJsonObject = new JSONObject();
                            postJsonObject = (JSONObject) posts.get(i);
                            String myPosts = (String)postJsonObject.get("content");
                            Posts.add(myPosts);
                        }

                        //LOGIN SUCCESS => direct to homepage
                    } else {

                        //LOGIN FAILURE => try again
                    }
                } catch (JSONException e) {
                    Log.e("catchE", "catche");

                    e.printStackTrace();
                }

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                // Pull out the first event on the public timeline


                // Do something with the response
                System.out.println(timeline);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                throwable.printStackTrace(System.out);
            }
        });
        //////////// SERVICE //////////////

        listViewP =(ListView)v.findViewById(R.id.listViewPost);
        Posts.add(Posts.toString());
        ArrayAdapter myAdapter = new ArrayAdapter<String>(getContext(),R.layout.simple_list_item_1, Posts);
        listViewP.setAdapter(myAdapter);


        return v;
    }
}