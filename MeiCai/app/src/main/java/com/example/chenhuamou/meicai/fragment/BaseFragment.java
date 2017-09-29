package com.example.chenhuamou.meicai.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by chenhuamou on 2017/9/29.
 */

public abstract class BaseFragment extends Fragment {

    public abstract  int getResource(); // 获取资源
    public abstract void init(View view); // 初始化组件
    public abstract void loadData(); // 加载数据
    public abstract void startdestory(); // 销毁数据，释放内存


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(getResource(), container, false);
        // 绑定
        ButterKnife.bind(this, view);
        init(view);
        loadData();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        startdestory();
        System.gc();
    }
}
