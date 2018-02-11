package com.vishwanath.sample.kubraandroidtest.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vishwanath.sample.kubraandroidtest.Model.Posts;
import com.vishwanath.sample.kubraandroidtest.PostService;
import com.vishwanath.sample.kubraandroidtest.PostUserActivity;
import com.vishwanath.sample.kubraandroidtest.R;

import java.util.List;

/**
 * Created by vishwanath on 2/11/18.
 */

public class PostUserAdapter extends BaseAdapter {

    private List<Posts> posts;
    private Context context;

    public PostUserAdapter(Context context, List<Posts> posts) {
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

        rowView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Intent intent = new Intent(context, PostService.class);
                intent.putExtra("user_post", "https://mobile-code-test.ifactornotifi.com/json/posts/2");
                context.startService(intent);
                // will create a context menu and display EDIT or DELETE option here by filling the array using arrayadapter
                // upon clicking, it will launch a service in the background which will edit/delete the user's data

                return false;
            }
        });
        return rowView;
    }
}