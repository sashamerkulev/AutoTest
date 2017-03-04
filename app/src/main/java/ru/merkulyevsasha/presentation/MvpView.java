package ru.merkulyevsasha.presentation;


public interface MvpView {

    void showProgress();
    void hideProgress();

    void showMessage(String message);

}
