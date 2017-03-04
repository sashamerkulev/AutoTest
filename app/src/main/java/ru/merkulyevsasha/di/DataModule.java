package ru.merkulyevsasha.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.merkulyevsasha.data.HttpTitlesService;


@Module
public class DataModule {

    @Singleton
    @Provides
    public HttpTitlesService getHttpTitlesService(){
        return new HttpTitlesService();
    }

}
