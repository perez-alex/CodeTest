package com.example.alexanderperez.devigetcodetest.services;

import com.example.alexanderperez.devigetcodetest.BuildConfig;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alexander.perez on 8/31/2017.
 */

public class Service {

    public static RedditService getRedditService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.REDDIT_URL)
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                                .create()
                ))
                .build();

        RedditService service = retrofit.create(RedditService.class);
        return service;
    }
}
