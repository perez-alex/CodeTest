package com.example.alexanderperez.devigetcodetest.presenter;

/**
 * Created by alexander.perez on 9/3/2017.
 */

public interface Presenter<V> {
    void onViewAttached(V view);

    void onViewDetached();

    void onDestroyed();
}
