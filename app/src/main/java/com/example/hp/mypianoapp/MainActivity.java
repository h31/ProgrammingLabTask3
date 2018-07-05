package com.example.hp.mypianoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private SoundPool soundPool;

    private AudioManager audioManager;

    private static final int MAX_STREAMS = 5;

    private static final int streamType = AudioManager.STREAM_MUSIC;

    private boolean loaded;

    private int soundIdC;
    private int soundIdD;
    private int soundIdE;
    private int soundIdF;
    private int soundIdG;
    private int soundIdA;
    private int soundIdB;
    private int soundIdCis;
    private int soundIdDis;
    private int soundIdFis;
    private int soundIdGis;
    private int soundIdAis;
    private float volume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);


        float currentVolumeIndex = (float) audioManager.getStreamVolume(streamType);


        float maxVolumeIndex = (float) audioManager.getStreamMaxVolume(streamType);


        this.volume = currentVolumeIndex / maxVolumeIndex;


        this.setVolumeControlStream(streamType);


        if (Build.VERSION.SDK_INT >= 21) {

            AudioAttributes audioAttrib = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            SoundPool.Builder builder = new SoundPool.Builder();
            builder.setAudioAttributes(audioAttrib).setMaxStreams(MAX_STREAMS);

            this.soundPool = builder.build();
        } else {

            this.soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        }


        this.soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                loaded = true;
            }
        });


        this.soundIdC = this.soundPool.load(this, R.raw.c, 1);

        this.soundIdD = this.soundPool.load(this, R.raw.d, 1);

        this.soundIdE = this.soundPool.load(this, R.raw.e, 1);

        this.soundIdF = this.soundPool.load(this, R.raw.f, 1);

        this.soundIdG = this.soundPool.load(this, R.raw.g, 1);

        this.soundIdA = this.soundPool.load(this, R.raw.a, 1);

        this.soundIdB = this.soundPool.load(this, R.raw.b, 1);

        this.soundIdCis = this.soundPool.load(this, R.raw.cis, 1);

        this.soundIdDis = this.soundPool.load(this, R.raw.dis, 1);

        this.soundIdFis = this.soundPool.load(this, R.raw.fis, 1);

        this.soundIdGis = this.soundPool.load(this, R.raw.gis, 1);

        this.soundIdAis = this.soundPool.load(this, R.raw.ais, 1);

    }

    public void playSoundC(View view) {
        if (loaded) {
            float leftVolumn = volume;
            float rightVolumn = volume;
            int streamId = this.soundPool.play(this.soundIdC, leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }

    public void playSoundCis(View view) {
        if (loaded) {
            float leftVolumn = volume;
            float rightVolumn = volume;
            int streamId = this.soundPool.play(this.soundIdCis, leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }

    public void playSoundD(View view) {
        if (loaded) {
            float leftVolumn = volume;
            float rightVolumn = volume;
            int streamId = this.soundPool.play(this.soundIdD, leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }

    public void playSoundDis(View view) {
        if (loaded) {
            float leftVolumn = volume;
            float rightVolumn = volume;
            int streamId = this.soundPool.play(this.soundIdDis, leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }

    public void playSoundE(View view) {
        if (loaded) {
            float leftVolumn = volume;
            float rightVolumn = volume;
            int streamId = this.soundPool.play(this.soundIdE, leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }

    public void playSoundF(View view) {
        if (loaded) {
            float leftVolumn = volume;
            float rightVolumn = volume;
            int streamId = this.soundPool.play(this.soundIdF, leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }

    public void playSoundFis(View view) {
        if (loaded) {
            float leftVolumn = volume;
            float rightVolumn = volume;
            int streamId = this.soundPool.play(this.soundIdFis, leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }

    public void playSoundG(View view) {
        if (loaded) {
            float leftVolumn = volume;
            float rightVolumn = volume;
            int streamId = this.soundPool.play(this.soundIdG, leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }

    public void playSoundGis(View view) {
        if (loaded) {
            float leftVolumn = volume;
            float rightVolumn = volume;
            int streamId = this.soundPool.play(this.soundIdGis, leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }

    public void playSoundA(View view) {
        if (loaded) {
            float leftVolumn = volume;
            float rightVolumn = volume;
            int streamId = this.soundPool.play(this.soundIdA, leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }

    public void playSoundAis(View view) {
        if (loaded) {
            float leftVolumn = volume;
            float rightVolumn = volume;
            int streamId = this.soundPool.play(this.soundIdAis, leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }

    public void playSoundB(View view) {
        if (loaded) {
            float leftVolumn = volume;
            float rightVolumn = volume;
            int streamId = this.soundPool.play(this.soundIdB, leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }

}
