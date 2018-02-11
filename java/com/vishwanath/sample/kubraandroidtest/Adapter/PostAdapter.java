package com.vishwanath.sample.kubraandroidtest.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vishwanath.sample.kubraandroidtest.Model.Posts;
import com.vishwanath.sample.kubraandroidtest.Model.Users;
import com.vishwanath.sample.kubraandroidtest.PostDetailActivity;
import com.vishwanath.sample.kubraandroidtest.PostUserActivity;
import com.vishwanath.sample.kubraandroidtest.R;

import java.util.List;

/**
 * Created by vishwanath on 2/11/18.
 */

public class PostAdapter extends BaseAdapter {

    private List<Posts> posts;
    private Context context;

    public PostAdapter(Context context, List<Posts> posts) {
        this.posts = posts;
        this.context = context;

    }

    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder {
        TextView title;
        TextView body;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Holder holder = new Holder();
        View rowView = inflater.inflate(R.layout.list_posts, null);
        holder.title = rowView.findViewById(R.id.title);
        holder.body = rowView.findViewById(R.id.body);
        holder.title.setText(posts.get(position).getTitle());
        holder.body.setText(posts.get(position).getBody());
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = String.valueOf(v.getTag());
                Intent myIntent = new Intent(context, PostUserActivity.class);
                myIntent.putExtra("userId", userId);
                context.startActivity(myIntent);
            }
        });

        return rowView;
    }
}