package com.example.malwina.edulandia;

public class Match {
    private int questionMatchImage;
    private int answerMatchImage;
    private int correctAnswerSound;

    public Match(int questionMatchImage, int answerMatchImage, int correctAnswerSound) {
        this.questionMatchImage = questionMatchImage;
        this.answerMatchImage = answerMatchImage;
        this.correctAnswerSound = correctAnswerSound;
    }

    public int getQuestionMatchImage() {
        return questionMatchImage;
    }

    public int getAnswerMatchImage() {
        return answerMatchImage;
    }

    public int getCorrectAnswerSound() {
        return correctAnswerSound;
    }

}
