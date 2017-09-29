package com.example.chenhuamou.meicai.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chenhuamou.meicai.R;

import butterknife.BindView;

/**
 * Created by chenhuamou on 2017/9/29.
 */

public class ShopcarFragment extends BaseFragment {

    @BindView(R.id.recycleView)
    RecyclerView mRecycleView;

    @BindView(R.id.tv_total_price)
    TextView tv_total_price;


    @Override
    public int getResource() {
        return R.layout.fragment_shopcar;
    }

    @Override
    public void init(View view) {
        String[] data = {"萝卜青菜，各有所爱",
                "萝卜青菜，各有所爱",
                "萝卜青菜，各有所爱",
                "萝卜青菜，各有所爱",
                "萝卜青菜，各有所爱",
                "萝卜青菜，各有所爱",
                "萝卜青菜，各有所爱",
                "萝卜青菜，各有所爱",
                "萝卜青菜，各有所爱",
                "萝卜青菜，各有所爱",
                "萝卜青菜，各有所爱",
                "萝卜青菜，各有所爱",
                "萝卜青菜，各有所爱",
                "萝卜青菜，各有所爱",
                "萝卜青菜，各有所爱",
                "萝卜青菜，各有所爱",
                "萝卜青菜，各有所爱",
                "萝卜青菜，各有所爱",
                "萝卜青菜，各有所爱",
                "萝卜青菜，各有所爱",
                "萝卜青菜，各有所爱",
                "萝卜青菜，各有所爱",
                "萝卜青菜，各有所爱",
                "萝卜青菜，各有所爱",
                "萝卜青菜，各有所爱",
                "萝卜青菜，各有所爱",
                "萝卜青菜，各有所爱",
                "萝卜青菜，各有所爱",
                "萝卜青菜，各有所爱",
                "萝卜青菜，各有所爱",
                "萝卜青菜，各有所爱",
                "萝卜青菜，各有所爱"

        };
        // 设置 LayoutManager
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        mRecycleView.setLayoutManager(llm);
        // 高度固定
        mRecycleView.setHasFixedSize(true);
        // 设置adapter
        mRecycleView.setAdapter(new MyShopcarAdapter(data));

        // 设置总价格变色
        String totalPriceString = tv_total_price.getText().toString();
        SpannableStringBuilder builder = new SpannableStringBuilder(totalPriceString);
        ForegroundColorSpan span = new ForegroundColorSpan(getResources().getColor(R.color.colorRed));
        builder.setSpan(span, 3, totalPriceString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_total_price.setText(builder);

    }

    @Override
    public void loadData() {

    }

    @Override
    public void startdestory() {

    }


    /*
    * recycleView adapter
    * */
    private class MyShopcarAdapter extends RecyclerView.Adapter {

        private String[] mData = null;

        public MyShopcarAdapter(String[] data) {
            mData = data;
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View cell = LayoutInflater.from(getActivity()).inflate(R.layout.view_shopcar_cell, parent, false);
            MyShopcarViewHolder viewHolder = new MyShopcarViewHolder(cell);
            return viewHolder;
        }


        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            // 赋值
            MyShopcarViewHolder viewHolder = (MyShopcarViewHolder) holder;

            // 已选多少斤
            String weightStr = "已选20斤";
            SpannableStringBuilder builderW = new SpannableStringBuilder(weightStr);
            ForegroundColorSpan span = new ForegroundColorSpan(getResources().getColor(R.color.colorRed));
            builderW.setSpan(span, 2, weightStr.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            viewHolder.tv_weight.setText(builderW);

            // 小计价格
            String priceStr = "小计：￥34.00";
            SpannableStringBuilder builder = new SpannableStringBuilder(priceStr);

            builder.setSpan(span, 3, priceStr.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            viewHolder.tv_price.setText(builder);
        }

        @Override
        public int getItemCount() {
            return mData.length;
        }

        /*
        * viewHolder
        * */
        private class MyShopcarViewHolder extends RecyclerView.ViewHolder {

            private TextView tv_name;
            private TextView tv_weight;
            private TextView tv_price;


            public MyShopcarViewHolder(View itemView) {
                super(itemView);
                tv_name = itemView.findViewById(R.id.tv_name);
                tv_weight = itemView.findViewById(R.id.tv_weight);
                tv_price = itemView.findViewById(R.id.tv_price);
            }
        }

    }
}
