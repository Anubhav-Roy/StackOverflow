package com.roy.anubhav.stackoverflow.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Class for handling sharedPreferences transactions
 * Made into a singleton and initialized in the ApplicationClass
 */
public class AppPreferences {

    public static AppPreferences settings;
    public SharedPreferences sharedPrefs;

    public static AppPreferences getInstance(Context context) {
        if (settings == null) {
            settings = new AppPreferences(context);
        }
        return settings;
    }

    public AppPreferences(Context context) {
        sharedPrefs = getSharedPreferences(context);
    }


    public static SharedPreferences getSharedPreferences(Context context) {
        if (context == null) {
            return null;
        }

        return context.getSharedPreferences("com.roy.anubhav.stackoverflow.preferences",
                Context.MODE_PRIVATE);
    }

}
