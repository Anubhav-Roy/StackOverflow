package com.roy.anubhav.stackoverflow.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


//Retrofit Interface for performing API calls
public interface ApiInterface {

    @GET("tags?order=desc&sort=popular&site=stackoverflow")
    Call<TagResponse> getTags();

    @GET("questions")
    Call<QuestionResponse> getQuestions(@Query("tagged") String tag , @Query("site") String site, @Query("sort") String sort);

}
