package com.xiaochui.tradecircle.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaochui.tradecircle.R;
import com.xiaochui.tradecircle.presenter.MainIndexPresenter;
import com.xiaochui.tradecircle.ui.adapter.CommonViewPagerAdapter;
import com.xiaochui.tradecircle.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainIndexFragment extends BaseFragment<MainIndexPresenter> implements AppBarLayout.OnOffsetChangedListener {


    @BindView(R.id.fragment_main_index_toolbar)
    Toolbar toolbar;
    @BindView(R.id.fragment_main_index_viewpager)
    ViewPager viewpager;
    @BindView(R.id.fragment_main_index_tablayout)
    TabLayout tablayout;
    @BindView(R.id.fragment_main_index_collapsing)
    CollapsingToolbarLayout collapsingLayout;
    @BindView(R.id.fragment_main_index_appbar_layout)
    AppBarLayout appbarLayout;

    private final int EXPANDED = 1;
    private final int COLLAPSED = 2;
    private final int INTERNEDIATE = 3;
    private CommonViewPagerAdapter adapter;
    private List<Fragment> fragmentList;
    private int state;


    @Override
    public MainIndexPresenter createPresenter() {
        return new MainIndexPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_index, container, false);
        return view;
    }

    @Override
    public void initEvent() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new InvitedFragment());
        fragmentList.add(new InvitedFragment());
        fragmentList.add(new InvitedFragment());
        adapter = new CommonViewPagerAdapter(getChildFragmentManager(), fragmentList);
        viewpager.setAdapter(adapter);
        tablayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        tablayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tablayout.setupWithViewPager(viewpager);

        appbarLayout.addOnOffsetChangedListener(this);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (verticalOffset == 0) {
            if (state != EXPANDED) {
                state = EXPANDED;//修改状态标记为展开
                toolbar.setVisibility(View.GONE);
            }
        } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
            if (state != COLLAPSED) {
                toolbar.setVisibility(View.VISIBLE);
                state = COLLAPSED;//修改状态标记为折叠
            }
        } else {
            if (state != INTERNEDIATE) {
                if (state == COLLAPSED) {
//                    playButton.setVisibility(View.GONE);//由折叠变为中间状态时隐藏播放按钮
                }
//                collapsingToolbarLayout.setTitle("INTERNEDIATE");//设置title为INTERNEDIATE
                state = INTERNEDIATE;//修改状态标记为中间
            }
        }

    }
}
