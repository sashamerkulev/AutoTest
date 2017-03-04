package ru.merkulyevsasha;

import android.app.Application;

import ru.merkulyevsasha.di.AppModule;
import ru.merkulyevsasha.di.DaggerTitlesComponent;
import ru.merkulyevsasha.di.DataModule;
import ru.merkulyevsasha.di.PresentersModule;
import ru.merkulyevsasha.di.TitlesComponent;


public class AutoTestApp extends Application {

    private TitlesComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerTitlesComponent.builder()
                .appModule(new AppModule(this))
                .dataModule(new DataModule())
                .presentersModule(new PresentersModule())
                .build();
    }

    public TitlesComponent getComponent() {
        return component;
    }


}
