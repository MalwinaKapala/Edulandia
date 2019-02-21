package com.example.malwina.edulandia;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class BalloonActivity extends AppCompatActivity
implements Balloon.BalloonListener {

    private static final String TAG = "MainActivity";

    private static final int BALLOONS_PER_LEVEL = 10;
    private static final int MIN_ANIMATION_DELAY = 500;
    private static final int MAX_ANIMATION_DELAY = 1500;
    private static final int MIN_ANIMATION_DURATION = 1000;
    private static final int MAX_ANIMATION_DURATION = 8000;
    private static final String ACTION_NEXT_LEVEL = "action_next_level";
    private static final String ACTION_RESTART_GAME = "action_restart_game";

    private ViewGroup mContentView;
    private List<Balloon> mBalloons = new ArrayList<>();
    private String mNextAction = ACTION_RESTART_GAME;
    private boolean mPlaying;
    private int[] mBalloonColors = new int[3];
    private int mNextColor, mBalloonsPopped,
            mScreenWidth, mScreenHeight;
    private MediaPlayer balloonMediaPlayer;
    private MediaPlayer poppingBalloonMediaPlayer;
    private ImageView reloadButton;
    private ImageView homeButton;
    private ImageView nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        balloonMediaPlayer = MediaPlayer.create(this, R.raw.gamebackgroundmusic1);
        balloonMediaPlayer.start();



//      Load the activity layout, which is an empty canvas
        setContentView(R.layout.balloon_activity);

//      Get background reference.
        mContentView = findViewById(R.id.balloonView);
        if (mContentView == null) throw new AssertionError();
        mContentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    setToFullScreen();
                }
                return false;
            }
        });
//        setToFullScreen();

//      After the layout is complete, get screen dimensions from the layout.
        ViewTreeObserver viewTreeObserver = mContentView.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    mContentView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    mScreenWidth = mContentView.getWidth();
                    mScreenHeight = mContentView.getHeight();
                    startGame();
                }
            });
        }

        reloadButton = findViewById(R.id.reloadButton);
        reloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("reload button", "clicked");
                onBackPressed();
                balloonMediaPlayer.stop();
                ((EdulandiaApplication)getApplication()).startMusic();

            }
        });

        homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("home button", "clicked");
                Intent myIntent = new Intent(BalloonActivity.this, MainActivity.class);
                startActivity(myIntent);
            }
        });

        nextButton = findViewById(R.id.goButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("next button", "clicked");
//                Intent myIntent = new Intent(BalloonActivity.this, FirstGameActivity.class);
//                startActivity(myIntent);

            }
        });



//      Initialize balloon colors: red, white and blue
        mBalloonColors[0] = Color.argb(255, 255, 0, 0);
        mBalloonColors[1] = Color.argb(255, 0, 255, 0);
        mBalloonColors[2] = Color.argb(255, 0, 0, 255);


    }



    @Override
    public void onBackPressed() {
        stopGame();
        super.onBackPressed();
    }

    private void setToFullScreen() {

        //      Set full screen mode
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    private void startGame() {

        setToFullScreen();

//      Start the first level
        startLevel();
    }

    private void stopGame() {
        mPlaying = false;
        gameOver(false);
    }

    private void startLevel() {

//      Reset flags for new level
        mPlaying = true;
        mBalloonsPopped = 0;

//      integer arg for BalloonLauncher indicates the level
        BalloonLauncher mLauncher = new BalloonLauncher();
        mLauncher.execute(1);

    }

    private void finishLevel() {
        mPlaying = false;
        mNextAction = ACTION_NEXT_LEVEL;
    }

    private void launchBalloon(int x) {

//      Balloon is launched from activity upon progress update from the AsyncTask
//      Create new imageview and set its tint color
        Balloon balloon = new Balloon(this, mBalloonColors[mNextColor], 150);
        mBalloons.add(balloon);

//      Reset color for next balloon
        if (mNextColor + 1 == mBalloonColors.length) {
            mNextColor = 0;
        } else {
            mNextColor++;
        }

//      Set balloon vertical position and dimensions, add to container
        balloon.setX(x);
        balloon.setY(mScreenHeight + balloon.getHeight());
        mContentView.addView(balloon);

//      Let 'er fly
        int duration = Math.max(MIN_ANIMATION_DURATION, MAX_ANIMATION_DURATION - (1 * 4000));
        balloon.releaseBalloon(mScreenHeight, duration);

    }

    @Override
    public void popBalloon(Balloon balloon, boolean userTouch) {

//      Play sound, make balloon go away
        poppingBalloonMediaPlayer = MediaPlayer.create(this, R.raw.goosesound);
        poppingBalloonMediaPlayer.start();


        mContentView.removeView(balloon);
        mBalloons.remove(balloon);
        mBalloonsPopped++;

        if (mBalloonsPopped == BALLOONS_PER_LEVEL) {
            finishLevel();
        }
    }

    private void gameOver(boolean allPinsUsed) {


//      Clean up balloons
        for (Balloon balloon : mBalloons) {
            balloon.setPopped(true);
        }

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (Balloon balloon : mBalloons) {
                    mContentView.removeView(balloon);
                }
                mBalloons.clear();
            }
        }, 2000);

//      Reset for a new game
        mPlaying = false;

    }

    private class BalloonLauncher extends AsyncTask<Integer, Integer, Void> {

        @Override
        protected Void doInBackground(Integer... params) {

            if (params.length != 1) {
                throw new AssertionError(
                        "Expected 1 param for current level");
            }

            int level = params[0];

//          level 1 = max delay; each ensuing level reduces delay by 500 ms
//            min delay is 250 ms
            int maxDelay = Math.max(MIN_ANIMATION_DELAY, (MAX_ANIMATION_DELAY - ((level - 1) * 500)));
            int minDelay = maxDelay / 2;

//          Keep on launching balloons until either
//              1) we run out or 2) the mPlaying flag is set to false
            int balloonsLaunched = 0;
            while (mPlaying && balloonsLaunched < BALLOONS_PER_LEVEL) {

//              Get a random horizontal position for the next balloon
                Random random = new Random(new Date().getTime());
                int xPosition = random.nextInt(mScreenWidth - 200);
                publishProgress(xPosition);
                balloonsLaunched++;

//              Wait a random number of milliseconds before looping
                int delay = random.nextInt(minDelay) + minDelay;
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return null;

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
//          This runs on the UI thread, so we can launch a balloon
//            at the randomized horizontal position
            int xPosition = values[0];
            launchBalloon(xPosition);
        }

    }


}

