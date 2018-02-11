package com.vishwanath.sample.kubraandroidtest;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.vishwanath.sample.kubraandroidtest.Adapter.UserAdapter;
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
import java.util.List;

public class MainActivity extends AppCompatActivity {


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
        UserTask mUserTask = new UserTask();
        mUserTask.execute();
    }

    private class UserTask extends AsyncTask<Void, Integer, List<Users>> {

        @Override
        protected List<Users> doInBackground(Void... params) {
            URL url;
            String response = "";
            String requestUrl = "https://mobile-code-test.ifactornotifi.com/json/users";
            List<Users> users = new ArrayList<>();
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
                        JSONObject addressJson = childJsonObj.getJSONObject("address");
                        JSONObject locationJson = addressJson.getJSONObject("geo");
                        UserAddress address = new UserAddress(addressJson.getString("street"),
                                addressJson.getString("suite"),
                                addressJson.getString("city"),
                                addressJson.getString("zipcode"),
                                locationJson.getString("lat"),
                                locationJson.getString("lng")
                        );
                        Users user = new Users(childJsonObj.getString("id"),
                                childJsonObj.getString("name"),
                                childJsonObj.getString("username"),
                                childJsonObj.getString("email"),
                                address);
                        users.add(user);
                    }
                }
                myConnection.disconnect();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

            }
            return users;
        }

        @Override
        protected void onPostExecute(List<Users> users) {
            super.onPostExecute(users);
            list.setAdapter(new UserAdapter(getApplicationContext(), users));
        }
    }
}
