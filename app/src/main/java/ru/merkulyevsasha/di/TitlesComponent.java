package ru.merkulyevsasha.di;


import javax.inject.Singleton;

import dagger.Component;
import ru.merkulyevsasha.presentation.details.TitleFragmentActivity;
import ru.merkulyevsasha.presentation.list.TitlesActivity;

@Singleton
@Component(modules={AppModule.class, DataModule.class, PresentersModule.class})
public interface TitlesComponent {

    void inject(TitlesActivity context);
    void inject(TitleFragmentActivity context);

}
