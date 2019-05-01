package com.roy.anubhav.stackoverflow.QuestionList.ViewQuestion;

import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.roy.anubhav.stackoverflow.ApplicationClass;
import com.roy.anubhav.stackoverflow.room.AddSavedQuestionToDB;
import com.roy.anubhav.stackoverflow.room.DeleteQuestionFromDB;
import com.roy.anubhav.stackoverflow.room.SavedQuestion;
import com.roy.anubhav.stackoverflow.room.SavedQuestionsDAO;

public class ViewQuestionViewModel extends ViewModel {

    //Retrieving SavedQuestionsDAO from the Application class
    final SavedQuestionsDAO savedQuestionsDAO = ((ApplicationClass) ApplicationClass.getContext()).getSavedQuestionsDAO();

    //LiveVariable for checking if the question is present on the device
    public MutableLiveData<Boolean> questionIsAvailableOffline = new MutableLiveData<>();

    //Check for the question on the device , whose title is @title
    public void checkIfQuestionIsAvailableOffline(String title){
        new CheckIfQuestionIsAvailableOffline().execute(title);
    }

    //Save the question in the DB
    public void saveQuestionOffline(String title,String url){

        //Convert the params into a Room Entity
        SavedQuestion savedQuestion = new SavedQuestion();
        savedQuestion.title = title;
        savedQuestion.link = url;
        new AddSavedQuestionToDB().execute(savedQuestion);
    }


    public void deleteQuestionFromDB(String title) {
        new DeleteQuestionFromDB().execute(title);
    }


        private class CheckIfQuestionIsAvailableOffline extends AsyncTask<String ,Void , Boolean>{
        @Override
        protected Boolean doInBackground(String... title) {

            if(savedQuestionsDAO.getSavedQuestion(title[0]).size()!=0)
                return true;
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            questionIsAvailableOffline.setValue(aBoolean);
        }
    }

}
