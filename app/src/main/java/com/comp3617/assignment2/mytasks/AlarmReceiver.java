package com.comp3617.assignment2.mytasks;

/**
 *
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    int notifyId=1;

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("*********", "onReceive Alarm...");
        Toast.makeText(context, "onReceive Alarm...", Toast.LENGTH_LONG).show();

    }
}
