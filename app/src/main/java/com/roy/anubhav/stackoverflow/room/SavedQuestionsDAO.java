package com.roy.anubhav.stackoverflow.room;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

// The DAO for storing questions
@Dao
public interface SavedQuestionsDAO {

    @Query("SELECT * FROM SavedQuestion")
    List<SavedQuestion> getAll();

    @Insert
    void insertSavedQuestion(SavedQuestion savedQuestion);

    @Query("DELETE FROM SavedQuestion WHERE title = :title")
    void deleteSavedQuestion(String title);

    @Query("SELECT * FROM SavedQuestion WHERE title = :title")
    List<SavedQuestion> getSavedQuestion(String title);


}
