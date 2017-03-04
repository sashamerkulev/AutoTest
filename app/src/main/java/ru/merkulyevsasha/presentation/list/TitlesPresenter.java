package ru.merkulyevsasha.presentation.list;


import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

import ru.merkulyevsasha.data.HttpTitlesService;
import ru.merkulyevsasha.model.TitleItem;
import ru.merkulyevsasha.presentation.MvpView;


public class TitlesPresenter {

    private TitlesView view;

    private final ExecutorService executor;
    private HttpTitlesService http;

    private ArrayList<TitleItem> lifeCicleCache;
    private ArrayList<TitleItem> lastResponse;

    public TitlesPresenter(HttpTitlesService http, ExecutorService executor){
        this.http = http;
        this.executor = executor;
        this.lifeCicleCache = null;
    }

    public void onStop(){
        this.view = null;
    }

    public void onStart(MvpView view){
        this.view = (TitlesView)view;
    }

    public void load() {

        if (this.lifeCicleCache != null){
            this.view.loadTitles(this.lifeCicleCache);
            this.lifeCicleCache = null;
            return;
        }

        showProgress();
        executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    //Thread.sleep(5000);
                    lastResponse = http.getTitles().execute().body();
                    if (view == null){
                        lifeCicleCache = lastResponse;
                    } else {
                        view.loadTitles(lastResponse);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    showMessage(e.getMessage());
                } finally{
                    hideProgress();
                }
            }
        });
    }

    public ArrayList<TitleItem> getLastResponse(){
        return lastResponse;
    }

    private void showMessage(String message){
        if (view != null){
            view.showMessage(message);
        }
    }

    private void showProgress(){
        if (view != null){
            view.showProgress();
        }
    }

    private void hideProgress(){
        if (view != null){
            view.hideProgress();
        }
    }



}
