package com.example.appsong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.appsong.databinding.ActivitySplashBinding;

import static java.lang.Thread.sleep;

public class SplashActivity extends AppCompatActivity {
    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=DataBindingUtil.setContentView(this,R.layout.activity_splash);


                Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(1000);
                    Intent intent= new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

    }
}




