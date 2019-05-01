package com.roy.anubhav.stackoverflow.UserInterest;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.roy.anubhav.stackoverflow.ApplicationClass;
import com.roy.anubhav.stackoverflow.retrofit.ApiInterface;
import com.roy.anubhav.stackoverflow.retrofit.Tag;
import com.roy.anubhav.stackoverflow.retrofit.TagResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserInterestViewModel extends ViewModel {

    //The variable that store the tags from the server
    private MutableLiveData<List<Tag>> tags;

    //Gets the tags from the server
    private void loadTags(){

        //Get the Retrofitclient from the application class
        ApiInterface apiService = ((ApplicationClass) ApplicationClass.getContext()).getRetrofitClient().create(ApiInterface.class);

        Call<TagResponse> call = apiService.getTags();
        call.enqueue(new Callback<TagResponse>() {
            @Override
            public void onResponse(Call<TagResponse> call, Response<TagResponse> response) {
                //Received the tags from the server and updating the variable so that the UI can update
                tags.setValue(response.body().getItems());
            }

            @Override
            public void onFailure(Call<TagResponse> call, Throwable t) {

            }
        });

    }


    public LiveData<List<Tag>> getTags() {
        if (tags == null) {                 //Tags haven't been received from the server
            tags = new MutableLiveData<List<Tag>>();
            loadTags();                     //Start loading the tags
        }
        return tags;
    }

}
