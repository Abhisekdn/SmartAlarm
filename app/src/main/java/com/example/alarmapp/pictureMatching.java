package com.example.alarmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class pictureMatching extends AppCompatActivity {
    int[] pos1 = {0,1,2,0,1,2};
    int [] pos = RandomizeArray(pos1);


    int currentPos = -1;
    ImageView curView = null;
    private int countPair = 0;
    int[] drawable = new int[]{
            R.drawable.camel,
            R.drawable.coala,
            R.drawable.tiger
//            R.drawable.lion,
//            R.drawable.fox,
//            R.drawable.monkey,
//            R.drawable.wolf,
//            R.drawable.mouse

    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_matching);
        ImageAdapter imageAdapter = new ImageAdapter(this);
        GridView gridView = (GridView)findViewById(R.id.gridView);
        gridView.setAdapter(imageAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (currentPos < 0 ) {
                    currentPos = position;
                    curView = (ImageView) view;
                    ((ImageView) view).setImageResource(drawable[pos[position]]);
                }
                else {
                    if (currentPos == position) {
                        ((ImageView) view).setImageResource(R.drawable.code);
                    } else if (pos[currentPos] != pos[position]) {
                        curView.setImageResource(R.drawable.code);
//                        Toast.makeText(MainActivity.this, "Not Match!", Toast.LENGTH_LONG).show();
                    } else {
                        ((ImageView) view).setImageResource(drawable[pos[position]]);
                        countPair++;
                        if (countPair == 3) {
                            Toast.makeText(pictureMatching.this, "Correct! Have a Great day", Toast.LENGTH_LONG).show();
                            CancelAlarm();
                            Intent i = new Intent(pictureMatching.this,MainActivity.class);
                            startActivity(i);
                        }
                    }
                    currentPos = -1;
                }
            }
        });
    }
    public static int[] RandomizeArray(int[] array){
        Random rgen = new Random();  // Random number generator

        for (int i=0; i<array.length; i++) {
            int randomPosition = rgen.nextInt(array.length);
            int temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition] = temp;
        }

        return array;
    }
    private void CancelAlarm() {
        Log.d("answer","Correct");
        Intent i = new Intent(pictureMatching.this,MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(pictureMatching.this,1,i,0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        Ringtone r;
        r = com.example.alarmapp.MyBroadcastReceiver.ringtone;
        alarmManager.cancel(pendingIntent);
        if(r.isPlaying())
            r.stop();
        MainActivity.called = false;

//        Toast.makeText(getApplicationContext(),"Alarm stopped",Toast.LENGTH_SHORT).show();


    }

}
