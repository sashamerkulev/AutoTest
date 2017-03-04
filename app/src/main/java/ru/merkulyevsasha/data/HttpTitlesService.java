package ru.merkulyevsasha.data;


import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

import ru.merkulyevsasha.model.TitleItem;
import rx.schedulers.Schedulers;

public class HttpTitlesService {

    interface HttpTitlesInterface {

        @GET("androids")
        Call<ArrayList<TitleItem>> getTitles();

    }

    private final HttpTitlesService.HttpTitlesInterface i;

    public HttpTitlesService(){

        RxJavaCallAdapterFactory adapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(" http://private-db05-jsontest111.apiary-mock.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                //.addCallAdapterFactory(adapter)
                .build();

        i = retrofit.create(HttpTitlesService.HttpTitlesInterface.class);
    }

    public Call<ArrayList<TitleItem>> getTitles() {
        return i.getTitles();
    }

}