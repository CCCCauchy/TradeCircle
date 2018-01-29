package com.xiaochui.tradecircle.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Android-Pc on 2017/4/11.
 */

public class BasePresenter<View> {
    //将所有正在处理的Subscription都添加到CompositeSubscription中。统一退出的时候注销观察
    private CompositeDisposable mCompositeDisposable;


    @Nullable
    private View mUi;

    /**
     * Called after the UI view has been created.  That is when fragment.onViewCreated() is called.
     *
     * @param ui The Ui implementation that is now ready to be used.
     */
    public void onUiReady(View ui) {
        mUi = ui;
    }

    /**
     * Called when the UI view is destroyed in Fragment.onDestroyView().
     */
    public final void onUiDestroy(View ui) {
        onUiUnready(ui);
        mUi = null;
        dispose();
    }

    /**
     * To be overriden by Presenter implementations.  Called when the fragment is being
     * destroyed but before ui is set to null.
     */
    public void onUiUnready(View ui) {
    }

    public void onSaveInstanceState(Bundle outState) {
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
    }

    public View getUi() {
        return mUi;
    }

    protected void addDisposable(Disposable subscription) {
        if (mCompositeDisposable == null || mCompositeDisposable.isDisposed()) { //csb 如果解绑了的话添加 sb 需要新的实例否则绑定时无效的
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }

    //在界面退出等需要解绑观察者的情况下调用此方法统一解绑，防止Rx造成的内存泄漏
    public void dispose() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }

    public String stringMinId(int minId) {
        return String.valueOf(minId);
    }

    public boolean legalString(String obj) {
        return obj != null && !obj.equals("");
    }
}
