package com.xiaochui.tradecircle.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.xiaochui.tradecircle.R;
import com.xiaochui.tradecircle.ui.base.BaseRecyclerViewAdapter;
import com.xiaochui.tradecircle.ui.base.BaseRecyclerViewHolder;
import com.xiaochui.tradecircle.ui.holder.InvitedViewHolder;

/**
 * @author cauchy
 * @date 2018/1/26 11:01
 * @since 1.0.0
 */

public class InvitedAdapter extends BaseRecyclerViewAdapter {
    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new InvitedViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_invited, parent, false));
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 322;
    }
}
