package com.roy.anubhav.stackoverflow;

import android.app.Application;
import android.content.Context;

import com.roy.anubhav.stackoverflow.dagger.components.DaggerRetrofitComponent;
import com.roy.anubhav.stackoverflow.dagger.components.DaggerRoomComponent;
import com.roy.anubhav.stackoverflow.dagger.components.RetrofitComponent;
import com.roy.anubhav.stackoverflow.dagger.components.RoomComponent;
import com.roy.anubhav.stackoverflow.dagger.modules.RoomModule;
import com.roy.anubhav.stackoverflow.room.SavedQuestionsDAO;
import com.roy.anubhav.stackoverflow.utils.AppPreferences;

import retrofit2.Retrofit;

/**
 * Application Class
 * Dagger Components are being instantiated here as well as the AppPreferences
 */


public class ApplicationClass extends Application {
    AppPreferences appPreferences;

    RetrofitComponent retrofitComponent;
    RoomComponent roomComponent;

    private static Context mContext;

    public static Context getContext() {
        //  return instance.getApplicationContext();
        return mContext;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();
        appPreferences = AppPreferences.getInstance(this);
        retrofitComponent = DaggerRetrofitComponent.create();

        roomComponent = DaggerRoomComponent.builder()
                .roomModule(new RoomModule(mContext))
                .build();

    }

    public AppPreferences getAppPreferences(){ return appPreferences;    }

    public Retrofit getRetrofitClient(){ return retrofitComponent.retrofitClient();}

    public SavedQuestionsDAO getSavedQuestionsDAO(){
        return roomComponent.savedQuestionDAO();
    }

}
