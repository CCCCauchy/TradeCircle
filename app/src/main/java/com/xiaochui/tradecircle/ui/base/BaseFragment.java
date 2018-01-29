package com.xiaochui.tradecircle.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaochui.tradecircle.presenter.BasePresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by thanatos on 15/12/21.
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment {

    private P mPresenter;

    public static final String BUNDLE_TYPE = "BUNDLE_TYPE";

    public abstract P createPresenter();

    private Unbinder unbinder;

    protected BaseFragment() {
        mPresenter = createPresenter();
    }

    public abstract void initEvent();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mPresenter.onRestoreInstanceState(savedInstanceState);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (unbinder == null) {
            unbinder = ButterKnife.bind(this, view);
        }
        initEvent();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.onUiReady(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.onUiDestroy(this);
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenter.onSaveInstanceState(outState);
    }


    public P getPresenter() {
        return mPresenter;
    }

}
