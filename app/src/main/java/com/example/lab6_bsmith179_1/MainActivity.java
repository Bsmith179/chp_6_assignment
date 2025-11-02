package com.example.lab6_bsmith179_1;

import android.media.MediaPlayer;
import android.os.Bundle;
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
        mpCats = new MediaPlayer();
        mpCats = MediaPlayer.create(this,R.raw.cats);
        playing = 0;
    }
    Button.OnClickListener Sad = new Button.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch(playing) {
                case 0:
                    mpSad.start();
                    playing = 1;
                    button1.setText("PAUSE SAD SONG");
                    button2.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    mpSad.pause();
                    playing = 0;
                    button1.setText("PLAY SAD SONG");
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
                    button2.setText("PAUSE CATS SONG");
                    button1.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    mpCats.pause();
                    playing = 0;
                    button2.setText("PLAY CATS SONG");
                    button1.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };


}