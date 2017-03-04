package ru.merkulyevsasha.di;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.merkulyevsasha.data.HttpTitlesService;
import ru.merkulyevsasha.presentation.list.TitlesPresenter;


@Module
public class PresentersModule {

    @Singleton
    @Provides
    public ExecutorService getExecutorService(){
        return Executors.newCachedThreadPool();
    }

    @Singleton
    @Provides
    public TitlesPresenter getTitlesPresenter(HttpTitlesService service, ExecutorService executor){
        return new TitlesPresenter(service, executor);
    }



}
