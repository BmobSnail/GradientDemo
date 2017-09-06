package com.snail.gradientdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.snail.gradientdemo.R;

/**
 * Created by snail
 * on 2017/9/5.
 * Todo
 */

public class DemoHolder extends RecyclerView.ViewHolder {

    private TextView mTxt;

    public DemoHolder(View itemView) {
        super(itemView);

        mTxt = itemView.findViewById(R.id.item_msg_txt);
    }

    public void bindData(String msg) {
        mTxt.setText(msg);
    }
}
