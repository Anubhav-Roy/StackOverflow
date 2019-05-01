package com.roy.anubhav.stackoverflow.dagger.modules;

import android.content.Context;

import androidx.room.Room;

import com.roy.anubhav.stackoverflow.room.SavedQuestionsDAO;
import com.roy.anubhav.stackoverflow.room.SavedQuestionsDB;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class RoomModule {

    private Context mContext;
    public RoomModule(Context context){
        this.mContext = context;
    }

    @Provides
    @Singleton
    SavedQuestionsDB provideSavedQuestionDB(){
        return Room.databaseBuilder(mContext,
                SavedQuestionsDB.class, "SearchedPlaces").build();
    }

    @Provides
    @Singleton
    SavedQuestionsDAO provideSavedQuestionsDAO(SavedQuestionsDB savedQuestionsDB){
        return savedQuestionsDB.savedQuestionsDAO();
    }
}
