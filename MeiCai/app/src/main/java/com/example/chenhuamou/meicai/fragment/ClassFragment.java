package com.example.chenhuamou.meicai.fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chenhuamou.meicai.R;
import com.example.chenhuamou.meicai.model.ProductClass;
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

public class ClassFragment extends BaseFragment {

    @BindView(R.id.recycleView_class)
    RecyclerView mRecycleView;

    private OkHttpClient mOkHttpClient = new OkHttpClient();

    private List<ProductClass> mList = new ArrayList<>();
    private MyAdapter mAdapter;

    private Handler mHandle = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mAdapter.notifyDataSetChanged();
        }
    };


    @Override
    public int getResource() {
        return R.layout.fragment_all_class;
    }

    @Override
    public void init(View view) {

        // 设置 layoutManager
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        mRecycleView.setLayoutManager(llm);

        mAdapter = new MyAdapter(mList);
        mRecycleView.setAdapter(mAdapter);
    }


    @Override
    public void loadData() {
        loadProductClassList();
    }

    private void loadProductClassList() {
        Request.Builder builder = new Request.Builder().url(ConstantUtil.BASE_URL + ConstantUtil.class_list);
        Request request = builder.build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Gson gson = new Gson();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("Data");
                    // 通过循环 取出一个个的 object
                    for(int i = 0; i < jsonArray.length(); i++) {
                        String objectStr = jsonArray.getString(i);
                        // 解析produc类
                        ProductClass productClass = gson.fromJson(objectStr, ProductClass.class);
                        // 添加到数组中
                        mList.add(productClass);
                    }
                    mHandle.sendEmptyMessage(0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public void startdestory() {

    }

    private class MyAdapter extends RecyclerView.Adapter {
        private List<ProductClass> mData;

        public MyAdapter(List<ProductClass> data) {
           mData = data;
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.view_all_class_cell, parent, false);
            MyViewHolder viewHolder = new MyViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MyViewHolder viewHolder = (MyViewHolder) holder;
            ProductClass productClass = mList.get(position);
            System.out.println("----------------------"+productClass.getClassname());
            viewHolder.textView.setText(productClass.getClassname());
            if (position == 1) {
                viewHolder.textView.setBackgroundColor(getResources().getColor(R.color.colorWhite));
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


}
