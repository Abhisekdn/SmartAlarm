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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.os.Bundle;
import java.util.Random;

public class countNumber extends AppCompatActivity {
    private imagesLib imageLib= new imagesLib();
    private EditText ans;
    private ImageView image;
    private Button submit;
    private String answer;
    private int PicNumber = 0;
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_number);

        ans = (EditText) findViewById(R.id.editText);
        image = (ImageView) findViewById(R.id.imageView);
        submit = (Button) findViewById(R.id.subButton);

        updatePicture();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ans.getText().toString().equalsIgnoreCase(answer)){
                    CancelAlarm();
                    Toast.makeText(countNumber.this, "Correct! Have a Great day", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(countNumber.this,MainActivity.class);
                    startActivity(i);
                }
                else{
                    updatePicture();
                    Toast.makeText(countNumber.this, "Wrong try again", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private void updatePicture() {
        PicNumber = random.nextInt(7);

        image.setImageResource(imageLib.getPicture(PicNumber));
        answer = imageLib.getCorrectAnswer(PicNumber);

        PicNumber = random.nextInt(7);


    }
    private void CancelAlarm() {
        Log.d("answer","Correct");
        Intent i = new Intent(countNumber.this,MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(countNumber.this,1,i,0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        Ringtone r;
        r = com.example.alarmapp.MyBroadcastReceiver.ringtone;
        alarmManager.cancel(pendingIntent);
        if(r.isPlaying())
            r.stop();
        MainActivity.called = false;

        Toast.makeText(getApplicationContext(),"Alarm stopped",Toast.LENGTH_SHORT).show();


    }

}
