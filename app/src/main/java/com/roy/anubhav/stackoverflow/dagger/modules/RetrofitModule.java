package com.roy.anubhav.stackoverflow.dagger.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {

    @Provides
    @Singleton
    Retrofit provideRetrofitClient(){
         final String BASE_URL = "https://api.stackexchange.com/2.2/";

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
