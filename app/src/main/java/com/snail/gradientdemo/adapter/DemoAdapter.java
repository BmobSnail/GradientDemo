package com.snail.gradientdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.snail.gradientdemo.R;

import java.util.List;

/**
 * Created by snail
 * on 2017/9/5.
 * Todo
 */

public class DemoAdapter extends RecyclerView.Adapter<DemoHolder> {

    private List<String> mData;

    public DemoAdapter(List<String> mData) {
        this.mData = mData;
    }

    @Override
    public DemoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_text,parent,false);
        return new DemoHolder(view);
    }

    @Override
    public void onBindViewHolder(DemoHolder holder, int position) {
        holder.bindData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
