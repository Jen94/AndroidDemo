package com.my.demo.Activities;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.my.demo.R;

public class AudioPlayerActivity extends Activity {

    private Button buttonPlayStop;
    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    public boolean playing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_player_activity);

        initViews();
    }

    private void initViews() {
        buttonPlayStop = (Button) findViewById(R.id.ButtonPlayStop);
        mediaPlayer = MediaPlayer.create(this, R.raw.simple_music);

        seekBar = (SeekBar) findViewById(R.id.seekBarAudioPlayer);
        seekBar.setMax(mediaPlayer.getDuration());
        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                seekChange(v);
                return false;
            }
        });

        buttonPlayStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAndStop();
            }
        });

        SeekBar seekBar2 = (SeekBar) findViewById(R.id.seekBarVolume);

        int maxVolume = getMaxVolume();
        seekBar2.setMax(maxVolume);
        int currentVolume = getCurrentVolume();
        seekBar2.setProgress(currentVolume);

        seekBar2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int value = ((SeekBar)v).getProgress();
                setVolume(value);
                return false;
            }
        });
    }

    public void setVolume(int val) {
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, val, 0);
    }

    public int getMaxVolume() {
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        return audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
    }

    public int getCurrentVolume() {
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        return audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
    }

    private void seekChange(View v){
        if(mediaPlayer.isPlaying()){
            SeekBar sb = (SeekBar)v;
            mediaPlayer.seekTo(sb.getProgress());
        }
    }

    public void playAndStop(){
        if (!playing) {
            PlayStop();
            try{
                mediaPlayer.start();
                startPlayProgressUpdater();
            }catch (IllegalStateException e) {
                mediaPlayer.pause();
            }
        }else {
            PlayStop();
            mediaPlayer.pause();
        }
    }

    public void startPlayProgressUpdater() {
        seekBar.setProgress(mediaPlayer.getCurrentPosition());

        Handler handler = new Handler();
        if (mediaPlayer.isPlaying()) {
            Runnable notification = new Runnable() {
                public void run() {
                    startPlayProgressUpdater();
                }
            };
            handler.postDelayed(notification,1000);
        }else{
            mediaPlayer.pause();
            PlayStop();
            seekBar.setProgress(0);
        }
    }

    public void PlayStop() {
        if (playing) {
            playing = false;
            buttonPlayStop.setText("PLAY");
        } else {
            playing = true;
            buttonPlayStop.setText("STOP");
        }
    }

}
