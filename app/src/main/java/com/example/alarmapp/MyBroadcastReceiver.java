package com.example.alarmapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.alarmapp.R;

public class MyBroadcastReceiver extends BroadcastReceiver {
    public static Ringtone ringtone;



    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context,"Time to get up!",Toast.LENGTH_SHORT).show();
        Vibrator vibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(2000);

        Notification noti = new Notification.Builder(context)
                .setContentTitle("Alarm is ON")
                .setContentText("You have set up the alarm")
                .setSmallIcon(R.mipmap.ic_launcher).build();

        NotificationManager manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        noti.flags|= Notification.FLAG_AUTO_CANCEL;
        manager.notify( 0,noti);


        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        ringtone = RingtoneManager.getRingtone(context,notification);
        ringtone.play();
        MainActivity.called = true;



//        setAlram.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

    }

}
