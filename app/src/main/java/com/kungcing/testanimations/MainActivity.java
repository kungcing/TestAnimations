package com.kungcing.testanimations;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = findViewById(R.id.imageView);
        Button btnViewPropertyAnimation = findViewById(R.id.btnViewProperty);
        Button btnObjectAnimator = findViewById(R.id.btnObjectAnimator);
        Button btnObjectAnimatorXml = findViewById(R.id.btnObjectAnimatorXml);
        Button btnYoyo = findViewById(R.id.btnYoyo);
        getSupportActionBar().hide();
        imageView.setY(-1000);
        btnViewPropertyAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setY(-1000);
                ViewPropertyAnimator anim = imageView.animate();
                anim.translationY(0);
                anim.setInterpolator(new BounceInterpolator());
                anim.setDuration(3000);
                anim.rotation(-360);
                anim.start();
            }
        });
        btnObjectAnimator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ObjectAnimator anim = new ObjectAnimator();
                anim.setTarget(imageView);
                anim.setProperty(View.ALPHA);
                anim.setFloatValues(1.0f,0.3f);
                anim.setDuration(200);
                anim.setRepeatCount(ObjectAnimator.INFINITE);
                anim.setRepeatMode(ObjectAnimator.REVERSE);

                ObjectAnimator anim2 = ObjectAnimator.ofFloat(imageView,View.ROTATION_Y,0,360);
                anim2.setDuration(1000);
                anim2.setRepeatCount(ObjectAnimator.INFINITE);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(anim,anim2);
                animatorSet.start();


            }
        });
        btnObjectAnimatorXml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setY(0);
                AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.first_animator);
                set.setTarget(imageView); // set the view you want to animate
                set.start();
            }
        });
        btnYoyo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setY(0);
                YoYo.with(Techniques.Shake).duration(1000).repeat(5).playOn(imageView);
                YoYo.with(Techniques.SlideInLeft).duration(1000).repeat(YoYo.INFINITE).playOn(imageView);
            }
        });

    }
}