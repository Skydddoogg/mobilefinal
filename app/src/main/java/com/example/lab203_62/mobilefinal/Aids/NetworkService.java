package com.example.lab203_62.mobilefinal.Aids;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class NetworkService {

    private Retrofit mRetrofit;
    private static NetworkService mInstance;
    private String BASE_URL = "https://jsonplaceholder.typicode.com";

    public NetworkService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkService getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkService();
        }
        return mInstance;
    }

//    public FriendService getUsers(){
//        return mRetrofit.create(FriendService.class);
//    }

}
