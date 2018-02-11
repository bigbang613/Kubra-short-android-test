package com.vishwanath.sample.kubraandroidtest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PostService extends Service {
    public PostService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);


        //String user_post = intent.getStringExtra("user_post");


    }
}
