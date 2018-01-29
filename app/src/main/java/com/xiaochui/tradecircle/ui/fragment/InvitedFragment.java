package com.xiaochui.tradecircle.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaochui.statefullayout.StatefulLayout;
import com.xiaochui.tradecircle.R;
import com.xiaochui.tradecircle.presenter.InvitedPresenter;
import com.xiaochui.tradecircle.ui.adapter.InvitedAdapter;
import com.xiaochui.tradecircle.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class InvitedFragment extends BaseFragment<InvitedPresenter> {


    @BindView(R.id.fragment_invited_recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.fragment_invited_stllayout)
    StatefulLayout statefulLayout;

    @Override
    public InvitedPresenter createPresenter() {
        return new InvitedPresenter();
    }

    @Override
    public void initEvent() {
        recyclerview.setAdapter(new InvitedAdapter());
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_invited, container, false);
        return view;
    }

}
