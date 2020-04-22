package com.example.alarmapp;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    TimePicker timePicker;
    TextView textView;
    int mhour,mMin;
    public static boolean called = false;

    Random random = new Random();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button setAlarm=(Button)findViewById(R.id.btStop);
        setAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Page_redirect();
            }
        });

        timePicker = (TimePicker) findViewById(R.id.timePicker);
        textView = (TextView) findViewById(R.id.timeTextView);
        timePicker.setIs24HourView(true);

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                mhour = hourOfDay;
                mMin = minute;

                textView.setText(textView.getText().toString()+" "+mhour+":"+mMin);

            }
        });
    }

    public void Page_redirect() {
        if(called==true) {
            int ran = random.nextInt(4);
//            int ran = 3;
            if(ran == 1) {
                Intent new_intent = new Intent(MainActivity.this, pictureMatching.class);
                startActivity(new_intent);
            }
            else if(ran == 2){
                Intent new_intent = new Intent(MainActivity.this, quiz.class);
                startActivity(new_intent);
            }
            else if(ran == 3){
                Intent new_intent = new Intent(MainActivity.this, countNumber.class);
                startActivity(new_intent);

            }


        }
        else
            Toast.makeText(getApplicationContext(),"Alarm is not Ringing",Toast.LENGTH_SHORT).show();
    }

    public void setTimer(View v)
    {
        if(called==false) {
            SharedPreferences preferences = getSharedPreferences("PREFS",0);
            boolean ifShowDailog1 = preferences.getBoolean("showDailog",true);
            if (ifShowDailog1){
                showDailog();
            }
//            showDailog();


            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            Date date = new Date();
            Calendar cal_alarm = Calendar.getInstance();
            Calendar cal_now = Calendar.getInstance();

            cal_now.setTime(date);
            cal_alarm.setTime(date);

            cal_alarm.set(Calendar.HOUR_OF_DAY, mhour);
            cal_alarm.set(Calendar.MINUTE, mMin);
            cal_alarm.set(Calendar.SECOND, 0);

            if (cal_alarm.before(cal_now)) {
                cal_alarm.add(Calendar.DATE, 1);

            }

            Intent i = new Intent(MainActivity.this, MyBroadcastReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 1, i, 0);

            alarmManager.set(AlarmManager.RTC_WAKEUP, cal_alarm.getTimeInMillis(), pendingIntent);


            timePicker = (TimePicker) findViewById(R.id.timePicker);
            int h, m;
            h = timePicker.getCurrentHour();
            m = timePicker.getCurrentMinute();

            Toast.makeText(getApplicationContext(), "Alarm set for " + h + ":" + m, Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"Stop the alarm to set the next one",Toast.LENGTH_SHORT).show();
        }


    }
    public void showDailog()
    {
        new AlertDialog.Builder(this)
                .setTitle("Important")
                .setMessage("Android requires all third party alarm apps to be running atleast in background for full functionality. Please remember to leave the app running before you go to bed. We also recommend that you have atleast 50% of your battery life")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNeutralButton("Never show this message", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        SharedPreferences preferences = getSharedPreferences("PREFS",0);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean("showDailog",false);
                        editor.apply();
                    }
                })
                .setIcon(R.drawable.triangle)
                .show();
    }

//    public void CancelAlarm(View v)
//    {
//
//        Intent i = new Intent(MainActivity.this,MyBroadcastReceiver.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,1,i,0);
//        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
//
//        Ringtone r;
//        r = com.example.alarmapp.MyBroadcastReceiver.ringtone;
//        alarmManager.cancel(pendingIntent);
//        if(r.isPlaying())
//            r.stop();
//
//        Toast.makeText(getApplicationContext(),"Alarm stopped",Toast.LENGTH_SHORT).show();
//
//    }
}
