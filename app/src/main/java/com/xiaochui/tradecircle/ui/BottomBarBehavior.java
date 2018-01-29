package com.xiaochui.tradecircle.ui;

import android.animation.Animator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;

/**
 * @author cauchy
 * @date 2018/1/29 10:40
 * @since 1.0.0
 */

public class BottomBarBehavior extends CoordinatorLayout.Behavior<View> {
    private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();

    private float viewY;//控件距离coordinatorLayout底部距离

    private float downY;
    private boolean isAnimate = false;//动画是否在进行

    public BottomBarBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //在嵌套滑动开始前回调
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {

        if (child.getVisibility() == View.VISIBLE && viewY == 0) {
            //获取控件距离父布局（coordinatorLayout）底部距离
            viewY = coordinatorLayout.getHeight() - child.getY();
        }
        Log.i("sysout", "onStartNestedScroll..." + nestedScrollAxes + "," + ((nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0));
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;//判断是否竖直滚动
    }

    //在嵌套滑动进行时，对象消费滚动距离前回调
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        //dy大于0是向上滚动 小于0是向下滚动
        Log.i("sysout", "onNestedPreScroll...dy = " + dy + ",getVisibility = " + child.getVisibility());
        if (dy >= 0 && !isAnimate && child.getVisibility() == View.VISIBLE) {
            hide(child);
        } else if (dy < 0 && !isAnimate && child.getVisibility() == View.GONE) {
            show(child);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(CoordinatorLayout parent, View child, MotionEvent ev) {
        Log.i("sysout", "  onInterceptTouchEvent.." + ev);
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            downY = ev.getY();
        }
        if (ev.getAction() == MotionEvent.ACTION_MOVE && !isAnimate) {
            if (ev.getY() > downY && child.getVisibility() == View.GONE) {
                show(child);
                downY = ev.getY();
            }
            if (ev.getY() < downY && child.getVisibility() == View.VISIBLE) {
                hide(child);
                downY = ev.getY();
            }
        }
        return super.onInterceptTouchEvent(parent, child, ev);
    }

    @Override
    public boolean onTouchEvent(CoordinatorLayout parent, View child, MotionEvent ev) {
        Log.i("sysout", "onTouchEvent.." + ev);
        return super.onTouchEvent(parent, child, ev);
    }

    //隐藏时的动画
    private void hide(final View view) {
        ViewPropertyAnimator animator = view.animate().translationY(viewY).setInterpolator(INTERPOLATOR).setDuration(500);

        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                isAnimate = true;
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                view.setVisibility(View.GONE);
                isAnimate = false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                show(view);
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        animator.start();
    }

    //显示时的动画
    private void show(final View view) {
        ViewPropertyAnimator animator = view.animate().translationY(0).setInterpolator(INTERPOLATOR).setDuration(500);
        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                view.setVisibility(View.VISIBLE);
                isAnimate = true;
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                isAnimate = false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                hide(view);
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        animator.start();
    }


}