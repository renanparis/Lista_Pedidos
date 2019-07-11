package com.paris.hayorders.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.paris.hayorders.R;
import com.paris.hayorders.activity.preferences.SplashScreenPreferences;

public class SplashActivity extends AppCompatActivity {

    public static final String KEY_PREFERENCES = "first_time_app";
    private Handler handler;
    private SplashScreenPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        preferences = new SplashScreenPreferences(this);
        handler = new Handler();

        configSharedPreferences();
    }

    private void configSharedPreferences() {
        if (preferences.contains(KEY_PREFERENCES)) {
            delayHalfSecond();

        } else {
            delayTwoSecond();

            preferences.addNotFirstTime(KEY_PREFERENCES);
        }
    }

    private void delayTwoSecond() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                goToCustomerActivity();
            }
        }, 2000);
    }

    private void delayHalfSecond() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                goToCustomerActivity();

            }
        }, 500);
    }

    private void goToCustomerActivity() {
        Intent intent = new Intent(this, CustomerListActivity.class);
        startActivity(intent);
        finish();
    }


}
