package com.example.chenhuamou.meicai.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chenhuamou.meicai.R;

import butterknife.BindView;

/**
 * Created by chenhuamou on 2017/9/29.
 */

public class ListFragment extends BaseFragment {


    @BindView(R.id.recycleView)
    RecyclerView mRecycleView;

    @Override
    public int getResource() {
        return R.layout.fragment_list;
    }

    @Override
    public void init(View view) {

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        mRecycleView.setLayoutManager(llm);

        // 设置Adapter
        mRecycleView.setAdapter(new ListViewAdapter());

    }

    @Override
    public void loadData() {

    }

    @Override
    public void startdestory() {

    }

    private class ListViewAdapter extends RecyclerView.Adapter {



        public ListViewAdapter() {

        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.view_list_cell, parent, false);
            ListViewHolder viewHolder = new ListViewHolder(view);
            return viewHolder;
        }


        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }


        @Override
        public int getItemCount() {
            return 20;
        }

        private class ListViewHolder extends RecyclerView.ViewHolder {

            public ListViewHolder(View itemView) {
                super(itemView);
            }
        }
    }

}
