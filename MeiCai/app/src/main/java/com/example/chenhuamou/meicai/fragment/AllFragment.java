package com.example.chenhuamou.meicai.fragment;

import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.View;

import com.example.chenhuamou.meicai.R;

import butterknife.BindView;

/**
 * Created by chenhuamou on 2017/9/29.
 */

public class AllFragment extends BaseFragment {

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;

    @Override
    public int getResource() {
        return R.layout.fragment_all;
    }

    @Override
    public void init(View view) {
        // 设置 tab
        String[] tabList = getResources().getStringArray(R.array.tabLayoutLists);
        for (int i = 0; i < tabList.length; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(tabList[i]));
        }

        mTabLayout.addOnTabSelectedListener(new TabSelectedListener());
    }

    @Override
    public void loadData() {

    }

    @Override
    public void startdestory() {

    }



    private class TabSelectedListener implements TabLayout.OnTabSelectedListener {

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            int index = tab.getPosition();
            Log.d("onTabSelected", "onTabSelected----------" + index);
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) { }


        @Override
        public void onTabReselected(TabLayout.Tab tab) { }
    }
}
