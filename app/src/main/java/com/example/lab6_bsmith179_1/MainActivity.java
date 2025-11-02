package com.example.lab6_bsmith179_1;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button button1, button2;
    MediaPlayer mpSad, mpCats;
    int playing;

    CountDownTimer sadTimer, catsTimer;
    private static final int SAD_TIMEOUT = 252000; // 4 min 12 sec
    private static final int CATS_TIMEOUT = 90000; // 1 min 20 sec


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        button1 = (Button) findViewById(R.id.btSad);
        button2 = (Button) findViewById(R.id.btCats);
        button1.setOnClickListener(Sad);
        button2.setOnClickListener(Cats);
        mpSad = new MediaPlayer();
        mpSad = MediaPlayer.create(this,R.raw.sad);
        mpSad.setLooping(true);
        mpCats = new MediaPlayer();
        mpCats = MediaPlayer.create(this,R.raw.cats);
        mpCats.setLooping(true);
        playing = 0;


    }
    Button.OnClickListener Sad = new Button.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch(playing) {
                case 0:
                    mpSad.start();
                    playing = 1;
                    button1.setText(R.string.btSadP);
                    button2.setVisibility(View.INVISIBLE);

                    sadTimer = new CountDownTimer(SAD_TIMEOUT, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                        }

                        @Override
                        public void onFinish() {
                            if (mpSad != null && mpSad.isPlaying()) {
                                mpSad.pause();
                                playing = 0;
                                button1.setText(R.string.btSad);
                                button2.setVisibility(View.VISIBLE);
                            }
                        }
                    }.start();
                    break;

                case 1:
                    if (sadTimer != null) sadTimer.cancel();
                    mpSad.pause();
                    playing = 0;
                    button1.setText(R.string.btSad);
                    button2.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };

    Button.OnClickListener Cats = new Button.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch(playing) {
                case 0:
                    mpCats.start();
                    playing = 1;
                    button2.setText(R.string.btCatsP);
                    button1.setVisibility(View.INVISIBLE);
                    catsTimer = new CountDownTimer(CATS_TIMEOUT, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                        }

                        @Override
                        public void onFinish() {
                            if (mpCats != null && mpCats.isPlaying()) {
                                mpCats.pause();
                                playing = 0;
                                button2.setText(R.string.btCats);
                                button1.setVisibility(View.VISIBLE);
                            }
                        }
                    }.start();
                    break;

                case 1:
                    if (catsTimer != null) catsTimer.cancel();
                    mpCats.pause();
                    playing = 0;
                    button2.setText(R.string.btCats);
                    button1.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };
    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Free up used resources
        if (mpSad != null) {
            mpSad.release();
            mpSad = null;
        }
        if (mpCats != null) {
            mpCats.release();
            mpCats = null;
        }

        // Cancel active timer
        if (sadTimer != null) sadTimer.cancel();
        if (catsTimer != null) catsTimer.cancel();
    }

}