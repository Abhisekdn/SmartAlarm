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
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class quiz extends AppCompatActivity {
    private QuestionLib mQuestionlib = new QuestionLib();
    private TextView Questions;
    private Button btchoice1;
    private Button btchoice2;
    private Button btchoice3;
    private String Answers;
    private int mQuestionNumber = 0;

    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Questions = (TextView) findViewById(R.id.question);
        btchoice1 = (Button) findViewById(R.id.choice1);
        btchoice2 = (Button) findViewById(R.id.choice2);
        btchoice3 = (Button) findViewById(R.id.choice3);
        btchoice3 = (Button) findViewById(R.id.choice3);

        updateQuestion();


        //Begin of button listener for button1
        btchoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btchoice1.getText() == Answers){
                    CancelAlarm();
                    Toast.makeText(quiz.this, "Correct! Have a Great day", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(quiz.this,MainActivity.class);
                    startActivity(i);
                }

                else{
                    updateQuestion();
                    Toast.makeText(quiz.this, "Wrong try again", Toast.LENGTH_SHORT).show();

                }
            }
        });
        //End of Button listener button1

        //Begin of button listener for button2
        btchoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btchoice2.getText() == Answers){
                    CancelAlarm();
                    Toast.makeText(quiz.this, "Correct! Have a Great day", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(quiz.this,MainActivity.class);
                    startActivity(i);
                }

                else{
                    updateQuestion();
                    Toast.makeText(quiz.this, "Wrong try again", Toast.LENGTH_SHORT).show();

                }
            }
        });
        //End of Button listener button2

        //Begin of button listener for button3
        btchoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btchoice3.getText() == Answers){
                    CancelAlarm();
                    Toast.makeText(quiz.this, "Correct! Have a Great day", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(quiz.this,MainActivity.class);
                    startActivity(i);
                }

                else{
                    updateQuestion();
                    Toast.makeText(quiz.this, "Wrong try again", Toast.LENGTH_SHORT).show();

                }
            }
        });
        //End of Button listener button3
    }

    private void updateQuestion() {
        mQuestionNumber = random.nextInt(10);

        Questions.setText(mQuestionlib.getQuestion(mQuestionNumber));
        btchoice1.setText(mQuestionlib.getChoice1(mQuestionNumber));
        btchoice2.setText(mQuestionlib.getChoice2(mQuestionNumber));
        btchoice3.setText(mQuestionlib.getChoice3(mQuestionNumber));

        Answers = mQuestionlib.getCorrectAnswer(mQuestionNumber);


        mQuestionNumber=random.nextInt(10);

    }

    private void CancelAlarm() {
        Log.d("answer","Correct");
        Intent i = new Intent(quiz.this,MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(quiz.this,1,i,0);
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
