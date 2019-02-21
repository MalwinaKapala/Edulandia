package com.example.malwina.edulandia;

import android.app.Application;
import android.media.MediaPlayer;
import android.widget.ImageView;
import android.widget.Toast;

public class EdulandiaApplication extends Application {
    public MediaPlayer musicMediaPlayer;
    private ImageView backButton;
    boolean soundOn = true;
    public MediaPlayer balloonMediaPlayer;


    @Override
    public void onCreate() {
        super.onCreate();
        musicMediaPlayer = MediaPlayer.create(this, R.raw.gamebackgroundmusic1);
        musicMediaPlayer.setLooping(true);
    }

    public void turnDownMusic() {
        musicMediaPlayer.setVolume(0.4f, 0.4f);
    }

    public void resetMusic() {
        musicMediaPlayer.setVolume(1f, 1f);
    }

    public void stopMusic() {
        musicMediaPlayer.stop();
    }
    public void pauseMusic() {
        musicMediaPlayer.pause();
    }

    public void startMusic() {
        musicMediaPlayer.start();
    }

    public void toggleMusic() {
        if (musicMediaPlayer.isPlaying()) {
            pauseMusic();
        } else {
            startMusic();
        }
    }

    public void musicOff() {
        if (musicMediaPlayer.isPlaying()) {
                Toast.makeText(EdulandiaApplication.this, "ON", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(EdulandiaApplication.this, "OFF", Toast.LENGTH_SHORT).show();

        }
    }

    public boolean isSoundOn() {
        return soundOn;
    }

    public void setSoundOff() {
        soundOn = false;
        Toast.makeText(EdulandiaApplication.this, "OFF", Toast.LENGTH_SHORT).show();
    }

    public void setSoundOn() {
        soundOn = true;
        Toast.makeText(EdulandiaApplication.this, "ON", Toast.LENGTH_SHORT).show();
    }

    public void startBalloonMusic() {
        balloonMediaPlayer.start();
    }

    public void pauseBalloonMusic() {
        balloonMediaPlayer.pause();
    }

    public void toggleBalloonMusic() {
        if (balloonMediaPlayer.isPlaying()) {
            pauseBalloonMusic();
        } else {
            startBalloonMusic();
        }
    }

}
