package com.xiaochui.tradecircle.presenter.callback;

/**
 * Created by WangXing on 2017/9/12.
 */

public interface ICommonCallback<T> {
    void loadDataSucceed(T data);

    void loadDataFailed(String message);
}
