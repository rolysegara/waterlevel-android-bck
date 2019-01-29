package com.visionet.indigo_waterlevel.baseapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

public abstract class MvpFragment<P extends BasePresenter> extends BaseFragment {
    protected P presenter;

    protected abstract P createPresenter();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
    }

    /*@Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        presenter = createPresenter();
        super.onViewCreated(view, savedInstanceState);
    }*/

    /*@Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        presenter = createPresenter();
        super.onCreate(savedInstanceState);
    }*/

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.dettachView();
        }
    }
}
