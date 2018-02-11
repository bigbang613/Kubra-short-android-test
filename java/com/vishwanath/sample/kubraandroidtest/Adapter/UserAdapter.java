package com.vishwanath.sample.kubraandroidtest.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vishwanath.sample.kubraandroidtest.Model.Users;
import com.vishwanath.sample.kubraandroidtest.PostDetailActivity;
import com.vishwanath.sample.kubraandroidtest.R;

import java.util.List;

/**
 * Created by vishwanath on 2/11/18.
 */

public class UserAdapter extends BaseAdapter {

    private List<Users> users;
    private Context context;

    public UserAdapter(Context context, List<Users> users) {
        this.users = users;
        this.context = context;

    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return Long.valueOf(users.get(position).getId());
    }

    public class Holder {
        TextView name;
        TextView address;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_users, null);
        Holder holder = new Holder();
        holder.name = rowView.findViewById(R.id.user);
        holder.address = rowView.findViewById(R.id.address);
        holder.name.setText(users.get(position).getUsername());
        holder.address.setText(users.get(position).getAddress().toString());
        rowView.setTag(users.get(position).getId());
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = String.valueOf(v.getTag());
                Intent myIntent = new Intent(context, PostDetailActivity.class);
                myIntent.putExtra("userId", userId);
                context.startActivity(myIntent);
            }
        });
        return rowView;
    }
}