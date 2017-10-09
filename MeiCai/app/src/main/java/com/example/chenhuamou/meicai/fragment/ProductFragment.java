package com.example.chenhuamou.meicai.fragment;

import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chenhuamou.meicai.R;
import com.example.chenhuamou.meicai.model.Product;
import com.example.chenhuamou.meicai.util.ConstantUtil;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by chenhuamou on 2017/9/29.
 */

public class ProductFragment extends BaseFragment {

    @BindView(R.id.recycleView)
    RecyclerView mRecycleView;

    private MyAdapter mAdapter;
    private List<Product> mProductList = new ArrayList<>();

    private OkHttpClient mOkHttpClient = new OkHttpClient();


    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mAdapter.notifyDataSetChanged();
        }
    };



    @Override
    public int getResource() {
        return R.layout.fragment_all_product;
    }

    @Override
    public void init(View view) {

        // 设置 layoutManager
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        mRecycleView.setLayoutManager(llm);

        mRecycleView.setHasFixedSize(true);
        // 设置 adapter
        mAdapter = new MyAdapter(mProductList);
        mRecycleView.setAdapter(mAdapter);



    }

    @Override
    public void loadData() {
        // 获取产品列表数据
        loadProductList();
    }

    @Override
    public void startdestory() { }

    /*
    * 获取产品详情列表
    * */
    private void loadProductList() {
        Request.Builder builder = new Request.Builder().url(ConstantUtil.BASE_URL + ConstantUtil.product_list);
        Request request = builder.build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("获取数据失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // 请求的结果
                String result = response.body().string();
                Gson gson = new Gson();
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("Data");
                    // 通过循环 取出一个个的 object
                    for(int i = 0; i < jsonArray.length(); i++) {
                        String objectStr = jsonArray.getString(i);
                        // 解析produc类
                        Product product = gson.fromJson(objectStr, Product.class);
                        // 添加到数组中
                        mProductList.add(product);
                    }
                    mHandler.sendEmptyMessage(0);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private class MyAdapter extends RecyclerView.Adapter {
        private List<Product> mList;

        public MyAdapter(List<Product> list) {
            mList = list;
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.view_all_product_cell, parent, false);
            MyPorductViewHolder viewHolder = new MyPorductViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            // 赋值
            MyPorductViewHolder viewHolder = (MyPorductViewHolder) holder;

            Product product = mList.get(position);
            // 产品图片
            Uri uri = Uri.parse(product.getProductpic());
            viewHolder.iv_pic.setImageURI(uri);

            // 名称
            viewHolder.tv_name.setText(product.getProductname());

            // 规格
            viewHolder.tv_weight.setText(product.getProductspec());

            // 单价
            String unitStr = "￥" + product.getProductpricethis() + "/斤";
            SpannableStringBuilder builder = new SpannableStringBuilder(unitStr);
            ForegroundColorSpan span = new ForegroundColorSpan(getResources().getColor(R.color.colorRed));
            AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan(44);
            builder.setSpan(span, 0, unitStr.length() - 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setSpan(sizeSpan, 0, unitStr.length() - 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            viewHolder.tv_unit_price.setText(builder);


        }

        @Override
        public int getItemCount() {
            return mList.size();
        }


        private class MyPorductViewHolder extends RecyclerView.ViewHolder {
            private ImageView iv_pic;
            private TextView tv_name;
            private TextView tv_weight;
            private TextView tv_unit_price;


            public MyPorductViewHolder(View itemView) {
                super(itemView);
                iv_pic = itemView.findViewById(R.id.iv_pic);
                tv_name = itemView.findViewById(R.id.tv_name);
                tv_weight = itemView.findViewById(R.id.tv_weight);
                tv_unit_price = itemView.findViewById(R.id.tv_unit_price);
            }
        }
    }
}
