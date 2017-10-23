package com.example.chenhuamou.meicai.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chenhuamou.meicai.R;
import com.example.chenhuamou.meicai.model.ProductClass;

import java.util.List;

/**
 * Created by chenhuamou on 2017/10/9.
 */

public class ProductClassAdapter extends RecyclerView.Adapter implements View.OnClickListener {

    private Context mContext;
    private List<ProductClass> mData;
    private OnItemClickListener mOnItemClickListener = null;

    public int currentIndex = 0;


    public ProductClassAdapter(Context context, List<ProductClass> list) {
        this.mContext = context;
        this.mData = list;
    }

    /*
    * View 的点击事件
    * */
    @Override
    public void onClick(View view) {
        // 把点击事件传递给自定义的接口
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(view, (int)view.getTag());
        }
    }


    /*
    * 定义点击的接口，让外部来实现
    * */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    /*
    * set 方法
    * */
    public void setmOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cell = LayoutInflater.from(mContext).inflate(R.layout.view_all_class_cell, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(cell);
        // 设置点击
        cell.setOnClickListener(this);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.itemView.setTag(position);
        ProductClass productClass = mData.get(position);
        viewHolder.textView.setText(productClass.getClassname());

        // 设置点击变色
        if (currentIndex == position) {
            viewHolder.itemView.setBackgroundColor(mContext.getResources().getColor(R.color.colorWhite));
        } else  {
            viewHolder.itemView.setBackgroundColor(mContext.getResources().getColor(R.color.backgroundColor));
        }

    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_name);
        }
    }

}
