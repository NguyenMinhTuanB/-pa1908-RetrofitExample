package com.example.retrofitexample;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static Retrofit sRetrofit;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    public static Retrofit getInstance(){
        if(sRetrofit == null){
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return sRetrofit;
    }
}
