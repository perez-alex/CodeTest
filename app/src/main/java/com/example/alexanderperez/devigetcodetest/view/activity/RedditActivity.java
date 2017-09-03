package com.example.alexanderperez.devigetcodetest.view.activity;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;

import com.example.alexanderperez.devigetcodetest.R;
import com.example.alexanderperez.devigetcodetest.presenter.RedditFactory;
import com.example.alexanderperez.devigetcodetest.presenter.PresenterLoader;
import com.example.alexanderperez.devigetcodetest.presenter.RedditPresenter;
import com.example.alexanderperez.devigetcodetest.utils.BusProvider;
import com.example.alexanderperez.devigetcodetest.view.RedditView;

public class RedditActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<RedditPresenter>{

    private RedditPresenter presenter;
    private static final int LOADER_ID = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_reddit);

//        RedditView view = new RedditView(this);
//        presenter = new RedditPresenter(new RedditModel(BusProvider.getInstance()), view);

        getSupportLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        BusProvider.register(presenter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        BusProvider.unregister(presenter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Ready to use presenter
        presenter.onViewAttached(this);
    }

    @Override
    protected void onStop() {
        presenter.onViewDetached();
        super.onStop();
    }

    @Override
    public Loader<RedditPresenter> onCreateLoader(int id, Bundle args) {
        return new PresenterLoader<>(this, new RedditFactory());
    }

    @Override
    public void onLoadFinished(Loader<RedditPresenter> loader, RedditPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onLoaderReset(Loader<RedditPresenter> loader) {
        presenter = null;
    }
}
