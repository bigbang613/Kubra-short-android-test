package com.vishwanath.sample.kubraandroidtest;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.vishwanath.sample.kubraandroidtest.Adapter.PostAdapter;
import com.vishwanath.sample.kubraandroidtest.Adapter.PostUserAdapter;
import com.vishwanath.sample.kubraandroidtest.Adapter.UserAdapter;
import com.vishwanath.sample.kubraandroidtest.Model.Posts;
import com.vishwanath.sample.kubraandroidtest.Model.UserAddress;
import com.vishwanath.sample.kubraandroidtest.Model.Users;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class PostUserActivity extends AppCompatActivity {

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = findViewById(R.id.userList);
    }

    @Override
    protected void onResume() {
        super.onResume();

        PostUserTask mPostUserTask = new PostUserTask();
        mPostUserTask.execute();
    }

    private class PostUserTask extends AsyncTask<Void, Integer, List<Posts>> {

        @Override
        protected List<Posts> doInBackground(Void... params) {
            URL url;
            String response = "";
            String requestUrl = "https://mobile-code-test.ifactornotifi.com/json/posts";
            List<Posts> posts = new ArrayList<>();
            String inputLine;
            try {
                url = new URL(requestUrl);
                HttpURLConnection myConnection = (HttpURLConnection) url.openConnection();
                myConnection.setRequestMethod("GET");

                myConnection.connect();
                InputStreamReader streamReader = new
                        InputStreamReader(myConnection.getInputStream());
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();
                while ((inputLine = reader.readLine()) != null) {
                    stringBuilder.append(inputLine);
                }
                reader.close();
                streamReader.close();
                response = stringBuilder.toString();

                JSONArray jsonArray = new JSONArray(response);
                if (jsonArray.length() > 0) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject childJsonObj = jsonArray.getJSONObject(i);
                        Posts post = new Posts(childJsonObj.getString("userId"),
                                childJsonObj.getString("id"),
                                childJsonObj.getString("title"),
                                childJsonObj.getString("body"));
                        posts.add(post);
                    }
                }
                myConnection.disconnect();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

            }
            return posts;
        }

        @Override
        protected void onPostExecute(List<Posts> posts) {
            super.onPostExecute(posts);
            list.setAdapter(new PostUserAdapter(getApplicationContext(), posts));
        }
    }
}
