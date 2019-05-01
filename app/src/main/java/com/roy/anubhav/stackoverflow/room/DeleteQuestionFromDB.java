package com.roy.anubhav.stackoverflow.room;

import android.os.AsyncTask;

import com.roy.anubhav.stackoverflow.ApplicationClass;

/**
 * Called when the user wishes to remove a question present on his phone.
 * Extends AsyncTask since DB transactions can't take place on UI thread
 */
public class DeleteQuestionFromDB extends AsyncTask<String,Void,Void> {

    //Get the DAO from the ApplicationClass
    final SavedQuestionsDAO savedQuestionsDAO = ((ApplicationClass) ApplicationClass.getContext()).getSavedQuestionsDAO();

    @Override
    protected Void doInBackground(String... title) {
        //Call the DAO method to remove the question , passing the title for the query parameter
        savedQuestionsDAO.deleteSavedQuestion(title[0]);
        return null;
    }
}
