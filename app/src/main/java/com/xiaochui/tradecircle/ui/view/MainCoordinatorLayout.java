package com.xiaochui.tradecircle.ui.view;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author cauchy
 * @date 2018/1/29 14:12
 * @since 1.0.0
 */

public class MainCoordinatorLayout extends CoordinatorLayout {
    public MainCoordinatorLayout(Context context) {
        super(context);
    }

    public MainCoordinatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MainCoordinatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        onInterceptTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }


    @Override
    public boolean onStartNestedScroll(View child, View target, int axes, int type) {
        return super.onStartNestedScroll(child, target, axes, type);
    }
}
