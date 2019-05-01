package com.roy.anubhav.stackoverflow.dagger.components;

import com.roy.anubhav.stackoverflow.dagger.modules.RetrofitModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = RetrofitModule.class)
public interface RetrofitComponent {

    Retrofit retrofitClient();

}
