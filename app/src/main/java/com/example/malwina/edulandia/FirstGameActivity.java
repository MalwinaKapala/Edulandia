package com.example.malwina.edulandia;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class FirstGameActivity extends AppCompatActivity {
    private ImageView backButton;
    private List<Match> matches;
    private ImageView questionPicture;
    private ImageView answer1;
    private ImageView answer2;
    private ImageView answer3;
    private ImageView answer4;
    private ImageView answer5;
    private ImageView answer6;
    private ImageView answer7;
    private ImageView answer8;
    private ImageView answer9;
    private static Random random;
    private int currentQuestionId;
    MediaPlayer soundsMediaPlayer;
    public MediaPlayer correctMediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_game_activity);

        random = new Random();
        matches = new ArrayList<>();
        matches.add(new Match(R.mipmap.cow1, R.mipmap.cow2, R.raw.trimmedcowsound));
        matches.add(new Match(R.mipmap.cat1, R.mipmap.cat2, R.raw.trimmedcatsound));
        matches.add(new Match(R.mipmap.goat1, R.mipmap.goat2, R.raw.trimmedgoatsound));
        matches.add(new Match(R.mipmap.dog1, R.mipmap.dog2, R.raw.trimmeddogsound));
        matches.add(new Match(R.mipmap.horse1, R.mipmap.horse2, R.raw.trimmedhorsesound));
        matches.add(new Match(R.mipmap.sheep1, R.mipmap.sheep2, R.raw.trimmedsheepsound));
        matches.add(new Match(R.mipmap.pig1, R.mipmap.pig2, R.raw.trimmedpigsound));
        matches.add(new Match(R.mipmap.hen1, R.mipmap.hen2, R.raw.trimmedhensound));
        matches.add(new Match(R.mipmap.goose1, R.mipmap.goose2, R.raw.goosesound));

        questionPicture = findViewById(R.id.questionPicture);
        final List<Integer> unAnsweredQuestions = new LinkedList<>();

        for (int i = 0; i < 9; i++) {
            unAnsweredQuestions.add(i);
        }

        Collections.shuffle(matches, random);
        Collections.shuffle(unAnsweredQuestions, random);
        currentQuestionId = unAnsweredQuestions.get(0);

        questionPicture.setImageResource(matches.get(currentQuestionId).getQuestionMatchImage());
        questionPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("play button: ", "clicked");

//                Intent myIntent = new Intent(MainActivity.this, GameMenuActivity.class);
//                startActivity(myIntent);
            }
        });

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("back button", "clicked");

                onBackPressed();
            }
        });



        final View.OnClickListener answersOnClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (soundsMediaPlayer != null && soundsMediaPlayer.isPlaying()) {
                    return;
                }

                final ImageView clickedImage = (ImageView) v;

                Log.d("answer image", "clicked");
                if (clickedImage.getTag().equals(matches.get(currentQuestionId).getAnswerMatchImage())) {
                    Log.d("answer","Good answer");
                    questionPicture.setVisibility(View.INVISIBLE);

                    ((EdulandiaApplication)getApplication()).startCorrectMP();

                    if (((EdulandiaApplication)getApplication()).isSoundOn()) {
                        soundsMediaPlayer = MediaPlayer.create(FirstGameActivity.this, matches.get(currentQuestionId).getCorrectAnswerSound());
                        soundsMediaPlayer.start();
                        soundsMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                nextQuestion(clickedImage, unAnsweredQuestions);
                            }
                        });
                    } else {
                        nextQuestion(clickedImage, unAnsweredQuestions);
                    }



                } else {
                    Log.d("answer", "Wrong answer");
                    ((EdulandiaApplication)getApplication()).startWrongMP();
                }
            }
        };



        answer1 = findViewById(R.id.answer1);
        answer1.setImageResource(matches.get(0).getAnswerMatchImage());
        answer1.setTag(matches.get(0).getAnswerMatchImage());
        answer1.setOnClickListener(answersOnClick);


        answer2 = findViewById(R.id.answer2);
        answer2.setImageResource(matches.get(1).getAnswerMatchImage());
        answer2.setTag(matches.get(1).getAnswerMatchImage());
        answer2.setOnClickListener(answersOnClick);

        answer3 = findViewById(R.id.answer3);
        answer3.setImageResource(matches.get(2).getAnswerMatchImage());
        answer3.setTag(matches.get(2).getAnswerMatchImage());
        answer3.setOnClickListener(answersOnClick);

        answer4 = findViewById(R.id.answer4);
        answer4.setImageResource(matches.get(3).getAnswerMatchImage());
        answer4.setTag(matches.get(3).getAnswerMatchImage());
        answer4.setOnClickListener(answersOnClick);

        answer5 = findViewById(R.id.answer5);
        answer5.setImageResource(matches.get(4).getAnswerMatchImage());
        answer5.setTag(matches.get(4).getAnswerMatchImage());
        answer5.setOnClickListener(answersOnClick);

        answer6 = findViewById(R.id.answer6);
        answer6.setImageResource(matches.get(5).getAnswerMatchImage());
        answer6.setTag(matches.get(5).getAnswerMatchImage());
        answer6.setOnClickListener(answersOnClick);

        answer7 = findViewById(R.id.answer7);
        answer7.setImageResource(matches.get(6).getAnswerMatchImage());
        answer7.setTag(matches.get(6).getAnswerMatchImage());
        answer7.setOnClickListener(answersOnClick);

        answer8 = findViewById(R.id.answer8);
        answer8.setImageResource(matches.get(7).getAnswerMatchImage());
        answer8.setTag(matches.get(7).getAnswerMatchImage());
        answer8.setOnClickListener(answersOnClick);

        answer9 = findViewById(R.id.answer9);
        answer9.setImageResource(matches.get(8).getAnswerMatchImage());
        answer9.setTag(matches.get(8).getAnswerMatchImage());
        answer9.setOnClickListener(answersOnClick);
//        Collections.shuffle(matches, new Random());
//        matches.size();
//        System.out.println("\nShuffled List withRandom(): \n" + matches);
    }

    private void nextQuestion(ImageView clickedImage, List<Integer>unAnsweredQuestions) {
        Toast.makeText(FirstGameActivity.this, "Finished", Toast.LENGTH_LONG).show();
        clickedImage.setImageResource(matches.get(currentQuestionId).getQuestionMatchImage());

        unAnsweredQuestions.removeAll(Collections.singletonList(currentQuestionId));

        if (unAnsweredQuestions.isEmpty()) {
            Toast.makeText(FirstGameActivity.this, "Wygrałeś", Toast.LENGTH_LONG).show();

            ((EdulandiaApplication)getApplication()).pauseMusic();


            Intent myIntent = new Intent(FirstGameActivity.this, BalloonActivity.class);
            startActivity(myIntent);

        } else {
            Collections.shuffle(unAnsweredQuestions, random);
            currentQuestionId = unAnsweredQuestions.get(0);
            questionPicture.setImageResource(matches.get(currentQuestionId).getQuestionMatchImage());
        }   questionPicture.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((EdulandiaApplication)getApplication()).turnDownMusic();

    }

    @Override
    protected void onPause() {
        super.onPause();
        ((EdulandiaApplication)getApplication()).resetMusic();


    }
}

