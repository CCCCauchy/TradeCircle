package com.xiaochui.tradecircle.ui.base;

import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.xiaochui.tradecircle.presenter.BasePresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.Utils;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;

/**
 * Created by Administrator on 2016/10/14.
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements SwipeBackActivityBase {
    private final String TAG = this.getClass().getSimpleName();
    protected SharedPreferences mSharedPreferences;
    protected Unbinder unbinder;
    private SwipeBackActivityHelper swipeBackActivityHelper;
    private P mPresenter;

    public abstract P createPresenter();

    public abstract BaseActivity getUi();

    public P getPresenter() {
        return mPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        if (savedInstanceState != null && mPresenter != null) {
            mPresenter.onRestoreInstanceState(savedInstanceState);
        }
        swipeBackActivityHelper = new SwipeBackActivityHelper(this);
        swipeBackActivityHelper.onActivityCreate();
//        mSharedPreferences = this.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME,
//                Context.MODE_WORLD_READABLE);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        unbinder = unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        swipeBackActivityHelper.onPostCreate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPresenter != null)
            mPresenter.onUiReady(getUi());
    }

    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return swipeBackActivityHelper.getSwipeBackLayout();
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {
        getSwipeBackLayout().setEnableGesture(enable);
    }

    @Override
    public void scrollToFinishActivity() {
        Utils.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();
    }

    protected String getAppVersionName() {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = getPackageManager();
            PackageInfo pi = pm.getPackageInfo(getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.i(TAG, "Exception", e);
        }
        return versionName;
    }


    protected void hideIME() {
        InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        mInputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    protected void showIME(View view) {
        InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        mInputMethodManager.showSoftInput(view, 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onUiDestroy(getUi());
        }
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
