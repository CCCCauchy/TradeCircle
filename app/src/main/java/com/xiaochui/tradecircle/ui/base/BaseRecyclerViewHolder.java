package com.xiaochui.tradecircle.ui.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xiaochui.tradecircle.ui.listener.OnRecyclerViewItemClickListener;


/**
 * @author Death丶Love
 * @project_name XiaoChuiProject
 * @package_name com.bocai.czeducation.adapters.viewpagerAdapters
 * @time 2017/8/22 16:34
 * @remark
 */

public abstract class BaseRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

    public BaseRecyclerViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindHolder(Object item);

    public String notNull(String obj) {
        return obj != null ? obj : "";
    }

    public boolean legalString(String obj) {
        return obj != null && !obj.equals("");
    }
}
