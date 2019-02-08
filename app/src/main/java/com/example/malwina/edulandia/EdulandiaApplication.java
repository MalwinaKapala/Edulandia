package com.example.malwina.edulandia;

import android.app.Application;
import android.media.Image;
import android.media.MediaPlayer;
import android.widget.ImageView;
import android.widget.Toast;

public class EdulandiaApplication extends Application {
    public MediaPlayer mediaPlayer;
    private ImageView backButton;

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.gamebackgroundmusic1);
        mediaPlayer.setLooping(true);
    }

    public void turnDownMusic() {
        mediaPlayer.setVolume(0.4f, 0.4f);
    }

    public void resetMusic() {
        mediaPlayer.setVolume(1f, 1f);
    }

    public void stopMusic() {
        mediaPlayer.stop();
    }
    public void pauseMusic() {
        mediaPlayer.pause();
    }

    public void startMusic() {
        mediaPlayer.start();
    }

    public void toggleMusic() {
        if (mediaPlayer.isPlaying()) {
            pauseMusic();
        } else {
            startMusic();
        }
    }

    public void musicOff() {
        if (mediaPlayer.isPlaying()) {
                Toast.makeText(EdulandiaApplication.this, "ON", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(EdulandiaApplication.this, "OFF", Toast.LENGTH_SHORT).show();

        }
    }
}
