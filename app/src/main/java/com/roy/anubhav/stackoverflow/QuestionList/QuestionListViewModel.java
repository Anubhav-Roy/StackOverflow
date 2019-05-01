package com.roy.anubhav.stackoverflow.QuestionList;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.roy.anubhav.stackoverflow.ApplicationClass;
import com.roy.anubhav.stackoverflow.retrofit.ApiInterface;
import com.roy.anubhav.stackoverflow.retrofit.Question;
import com.roy.anubhav.stackoverflow.retrofit.QuestionResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A common ViewModel for all the tabs which we fetch questions for a given tag but different sort
 */

public class QuestionListViewModel extends ViewModel {

    private MutableLiveData<List<Question>> questions;

    //Load Questions for the given tag and sort
    public void loadQuestion(String tag , String sort){
        ApiInterface apiService = ((ApplicationClass) ApplicationClass.getContext()).getRetrofitClient().create(ApiInterface.class);

        //Make the API call for getting the questions and parameters = tag and sort.
        //Hardcoded "stackoverflow" since that isn't going to change

        Call<QuestionResponse> call = apiService.getQuestions(tag,"stackoverflow",sort);
        call.enqueue(new Callback<QuestionResponse>() {
            @Override
            public void onResponse(Call<QuestionResponse> call, Response<QuestionResponse> response) {
                //Received the results , update the UI with the results
                questions.setValue(response.body().getItems());
            }

            @Override
            public void onFailure(Call<QuestionResponse> call, Throwable t) {

            }
        });
    }

    //Retrieve questions for the given tag and sort
    public MutableLiveData<List<Question>> getQuestions(String tag , String sort) {
        if (questions == null) {
            questions = new MutableLiveData<>();
            loadQuestion(tag,sort);
        }
        return questions;
    }
}
