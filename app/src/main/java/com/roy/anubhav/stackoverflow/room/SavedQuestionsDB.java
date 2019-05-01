package com.roy.anubhav.stackoverflow.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

//Database Class
@Database(entities={ SavedQuestion.class}, version=2,exportSchema = false)
public abstract class SavedQuestionsDB extends RoomDatabase {

    // Declare your data access objects as abstract
    public abstract SavedQuestionsDAO savedQuestionsDAO();



}
