package com.example.alarmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
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

        Toast.makeText(getApplicationContext(),"Please do NOT close the application",Toast.LENGTH_SHORT).show();
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        Date date= new Date();
        Calendar cal_alarm = Calendar.getInstance();
        Calendar cal_now = Calendar.getInstance();

        cal_now.setTime(date);
        cal_alarm.setTime(date);

        cal_alarm.set(Calendar.HOUR_OF_DAY,mhour);
        cal_alarm.set(Calendar.MINUTE,mMin);
        cal_alarm.set(Calendar.SECOND,0);

        if(cal_alarm.before(cal_now))
        {
            cal_alarm.add(Calendar.DATE,1);

        }

        Intent i = new Intent(MainActivity.this,MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,1,i,0);

        alarmManager.set(AlarmManager.RTC_WAKEUP,cal_alarm.getTimeInMillis(),pendingIntent);



        timePicker = (TimePicker) findViewById(R.id.timePicker);
        int h,m;
        h=timePicker.getCurrentHour();
        m = timePicker.getCurrentMinute();

        Toast.makeText(getApplicationContext(),"Alarm set for "+h+":"+m,Toast.LENGTH_SHORT).show();


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
