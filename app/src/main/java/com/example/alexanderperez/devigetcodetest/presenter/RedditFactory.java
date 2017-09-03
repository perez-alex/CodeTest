package com.example.alexanderperez.devigetcodetest.presenter;

import com.example.alexanderperez.devigetcodetest.model.RedditModel;
import com.example.alexanderperez.devigetcodetest.utils.BusProvider;

/**
 * Created by alexander.perez on 9/3/2017.
 */

public class RedditFactory implements PresenterFactory<RedditPresenter>{
    @Override
    public RedditPresenter create() {
        RedditPresenter presenter = new RedditPresenter();
        presenter.setModel(new RedditModel(BusProvider.getInstance()));
        return presenter;
    }
}
