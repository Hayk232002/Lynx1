package com.example.lynx1;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class StartPage extends AppCompatActivity {
    private ImageView img_start_animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_page);
        img_start_animation = (ImageView)  findViewById(R.id.img_start_animation);

        img_start_animation.post(new Runnable() {
            @Override
            public void run() {
                ((AnimationDrawable) img_start_animation.getBackground()).start();

                }

        });
    }
}
