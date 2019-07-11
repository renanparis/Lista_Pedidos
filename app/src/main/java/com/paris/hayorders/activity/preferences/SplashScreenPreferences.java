package com.paris.hayorders.activity.preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class SplashScreenPreferences {

    public static final String SPLASH_SCREEN_PREFERENCES = "com.paris.hayorders.activity.preferences.SplashScreenPreferences";
    private Context context;

    public SplashScreenPreferences(Context context) {
        this.context = context;
    }


    public boolean contains(String key) {
        SharedPreferences preferences = getSharedPreferences();
        return preferences.contains(key);

    }

    private SharedPreferences getSharedPreferences() {
        return context.getSharedPreferences(SPLASH_SCREEN_PREFERENCES, context.MODE_PRIVATE);
    }

    public void addNotFirstTime(String key) {

        SharedPreferences preferences = getSharedPreferences();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, true);
        editor.commit();

    }
}
