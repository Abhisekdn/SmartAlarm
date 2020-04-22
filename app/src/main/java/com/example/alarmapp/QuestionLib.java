package com.example.alarmapp;

public class QuestionLib {
    private String mQuestions [] = {
            "Which part of the plant holds it in the soil?",
            "This part of the plant absorbs energy from the sun.",
            "This part of the plant attracts bees, butterflies and hummingbirds.",
            "The _______ holds the plant upright.",
            "24+39=___",
            "56+44-1=___",
            "You are in a race, if you pass the person who is in the second position. Which position will you be in?",
            "Which is the tallest mountain in the world?",
            "Full Form of WWW"




    };


    private String mChoices [][] = {
            {"Roots", "Stem", "Flower"},
            {"Fruit", "Leaves", "Seeds"},
            {"Bark", "Flower", "Roots"},
            {"Flower", "Leaves", "Stem"},
            {"45","63","64"},
            {"99","89","101"},
            {"1st","2nd","3rd"},
            {"Mount Fuji","Mount Everest","Mount Kilimanjaro"},
            {"Wide World Web","World Water Welfare","World Wide Web"}


    };



    private String mCorrectAnswers[] = {"Roots", "Leaves", "Flower", "Stem","63","99","2nd","Mount Everest","World Wide Web"};



    public String getQuestion(int a) {
        String question = mQuestions[a];
        return question;
    }


    public String getChoice1(int a) {
        String choice0 = mChoices[a][0];
        return choice0;
    }


    public String getChoice2(int a) {
        String choice1 = mChoices[a][1];
        return choice1;
    }

    public String getChoice3(int a) {
        String choice2 = mChoices[a][2];
        return choice2;
    }

    public String getCorrectAnswer(int a) {
        String answer = mCorrectAnswers[a];
        return answer;
    }
}
