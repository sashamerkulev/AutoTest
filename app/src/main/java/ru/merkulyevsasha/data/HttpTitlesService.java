package ru.merkulyevsasha.data;


import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

import ru.merkulyevsasha.model.TitleItem;

public class HttpTitlesService {

    interface HttpTitlesInterface {

        @GET("androids")
        Call<ArrayList<TitleItem>> getTitles();

    }

    private final HttpTitlesService.HttpTitlesInterface anInterface;

    public HttpTitlesService(){

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(" http://private-db05-jsontest111.apiary-mock.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        anInterface = retrofit.create(HttpTitlesService.HttpTitlesInterface.class);
    }

    public Call<ArrayList<TitleItem>> getTitles() {
        return anInterface.getTitles();
    }

}