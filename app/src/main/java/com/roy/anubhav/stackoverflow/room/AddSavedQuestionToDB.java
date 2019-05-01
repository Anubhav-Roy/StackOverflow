package com.roy.anubhav.stackoverflow.room;

import android.os.AsyncTask;

import com.roy.anubhav.stackoverflow.ApplicationClass;
import com.roy.anubhav.stackoverflow.retrofit.Question;

/**
    Called when the user wishes to save a question on his phone
    Extends AsyncTask since DB transactions can't take place on UI thread
 */
public class AddSavedQuestionToDB extends AsyncTask<SavedQuestion,Void , Void> {

    //Get the DAO from the ApplicationClass
    final SavedQuestionsDAO savedQuestionsDAO = ((ApplicationClass) ApplicationClass.getContext()).getSavedQuestionsDAO();

    @Override
    protected Void doInBackground(SavedQuestion... savedQuestion) {

        try {
            savedQuestionsDAO.insertSavedQuestion(savedQuestion[0]);        //Call the insertSavedQuestion to store the question
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
