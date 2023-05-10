package com.mobile.pharmacy;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);

        ObjectAnimator anx = ObjectAnimator.ofFloat(button, "translationX", 100f);
        anx.setDuration(1000);
        ObjectAnimator any = ObjectAnimator.ofFloat(button, "translationY", 100f);
        any.setDuration(1000);

        AnimatorSet bouncer = new AnimatorSet();
        bouncer.play(any).before(anx);

        ValueAnimator fadeAnim = ValueAnimator.ofFloat(1f, 0f);
        fadeAnim.setDuration(250);

        AnimatorSet an_set = new AnimatorSet();
        an_set.play(bouncer).before(fadeAnim);
        an_set.start();
    }
}