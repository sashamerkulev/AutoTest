package ru.merkulyevsasha.presentation.list;

import java.util.List;

import ru.merkulyevsasha.model.TitleItem;
import ru.merkulyevsasha.presentation.MvpView;


public interface TitlesView extends MvpView {

    void loadTitles(List<TitleItem> titles);

}
