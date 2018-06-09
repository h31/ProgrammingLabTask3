package molkos.drums;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

    private SoundPool soundPool;
    private AudioManager audioManager;

    private static final int MAX_STREAMS = 5;

    private static final int streamType = AudioManager.STREAM_MUSIC;

    private boolean loaded;

    private int soundIdSplash;
    private int soundIdTopHat;
    private int soundIdHiHat;
    private int soundIdRide;
    private int soundIdChina;
    private int soundIdTomTom;
    private int soundIdFloorTom;
    private int soundIdBassDrum;
    private int soundIdSnareDrum;
    private float volume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

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

        this.soundIdSplash = this.soundPool.load(this, R.raw.splash, 1);
        this.soundIdTopHat = this.soundPool.load(this, R.raw.top_hat, 1);
        this.soundIdHiHat = this.soundPool.load(this, R.raw.hi_hat, 1);
        this.soundIdRide = this.soundPool.load(this, R.raw.ride, 1);
        this.soundIdChina = this.soundPool.load(this, R.raw.china, 1);
        this.soundIdFloorTom = this.soundPool.load(this, R.raw.floor_tom, 1);
        this.soundIdTomTom = this.soundPool.load(this, R.raw.tom_tom, 1);
        this.soundIdBassDrum = this.soundPool.load(this, R.raw.bass_drum, 1);
        this.soundIdSnareDrum = this.soundPool.load(this, R.raw.snare_drum, 1);
    }

    public void playSoundSplash(View view) {
        if (loaded) {
            float leftVolumn = volume;
            float rightVolumn = volume;

            int streamId = this.soundPool.play(this.soundIdSplash, leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }

    public void playSoundTopHat(View view) {
        if (loaded) {
            float leftVolumn = volume;
            float rightVolumn = volume;

            int streamId = this.soundPool.play(this.soundIdTopHat, leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }

    public void playSoundHiHat(View view) {
        if (loaded) {
            float leftVolumn = volume;
            float rightVolumn = volume;

            int streamId = this.soundPool.play(this.soundIdHiHat, leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }

    public void playSoundRide(View view) {
        if (loaded) {
            float leftVolumn = volume;
            float rightVolumn = volume;

            int streamId = this.soundPool.play(this.soundIdRide, leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }

    public void playSoundChina(View view) {
        if (loaded) {
            float leftVolumn = volume;
            float rightVolumn = volume;

            int streamId = this.soundPool.play(this.soundIdChina, leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }

    public void playSoundFloorTom(View view) {
        if (loaded) {
            float leftVolumn = volume;
            float rightVolumn = volume;

            int streamId = this.soundPool.play(this.soundIdFloorTom, leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }

    public void playSoundTomTom(View view) {
        if (loaded) {
            float leftVolumn = volume;
            float rightVolumn = volume;

            int streamId = this.soundPool.play(this.soundIdTomTom, leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }

    public void playSoundBassDrum(View view) {
        if (loaded) {
            float leftVolumn = volume;
            float rightVolumn = volume;

            int streamId = this.soundPool.play(this.soundIdBassDrum, leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }

    public void playSoundHSnareDrum(View view) {
        if (loaded) {
            float leftVolumn = volume;
            float rightVolumn = volume;

            int streamId = this.soundPool.play(this.soundIdSnareDrum, leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }
}
