package com.example.alarmapp;

public class imagesLib {
    private int [] dots = new int[]{
            R.drawable.four,
            R.drawable.six,
            R.drawable.nine,
            R.drawable.eleven,
            R.drawable.five,
            R.drawable.eight,
            R.drawable.ten,

    };
    private String [] correctAnswers = {"4","6","9","11","5","8","10"};

    public int getPicture(int a){
        int pic = dots[a];
        return pic;
    }

    public String getCorrectAnswer(int a){
        String answer= correctAnswers[a];
        return answer;
    }

}
