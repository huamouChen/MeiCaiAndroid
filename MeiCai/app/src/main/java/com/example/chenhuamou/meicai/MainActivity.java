package com.example.chenhuamou.meicai;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chenhuamou.meicai.fragment.AllFragment;
import com.example.chenhuamou.meicai.fragment.ListFragment;
import com.example.chenhuamou.meicai.fragment.MineFragment;
import com.example.chenhuamou.meicai.fragment.ShopcarFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private Context mContext;


    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @BindView(R.id.tv_tab_all)
    TextView tv_all;
    @BindView(R.id.img_all)
    ImageView iv_all;

    @BindView(R.id.tv_tab_list)
    TextView tv_tab_list;
    @BindView(R.id.img_list)
    ImageView iv_list;

    @BindView(R.id.tv_tab_shopcar)
    TextView tv_tab_shopcar;
    @BindView(R.id.img_shopcar)
    ImageView iv_shopcar;

    @BindView(R.id.tv_tab_mine)
    TextView tv_tab_mine;
    @BindView(R.id.img_mine)
    ImageView iv_mine;

    // tab bar 默认选中值
    public int mSelectIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // bind
        ButterKnife.bind(this);
        mContext = this;

        // tabBar 默认选中0
        setTabBarSelectedIndex(mSelectIndex);


        mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        // 设置 最大缓存数， 不然每次都会重新创建，然后 首页 嵌套的两个fragment也会被重新创建，然后就冲突出错
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.addOnPageChangeListener(this);
    }

    /*
    * 点击tabBar 按钮
    * */
    @OnClick({R.id.tab_ll_all,R.id.tab_ll_list, R.id.tab_ll_shopcar, R.id.tab_ll_mine})
    public void onClicked(View view) {
        // 把所有按钮设置为非选中状态
        resetTabBarSelectState();
        int index = 0;
        switch (view.getId()) {
            case R.id.tab_ll_all:
                index = 0;
                break;
            case R.id.tab_ll_list:
                index = 1;
                break;
            case R.id.tab_ll_shopcar:
                index = 2;
                break;
            case R.id.tab_ll_mine:
                index = 3;
                break;
            default:
                break;
        }
        // 根据 ID 来设置 tab bar 按钮的选中状态
        setTabBarSelectedIndex(index);
        // viewPager 滑动到对应的 fragment 页面，关闭滑动动画效果
        mViewPager.setCurrentItem(index, false);
    }

    /*
    * 把 tab bar 的按钮全部设置为非选中状态
    * */
    private void resetTabBarSelectState() {
        tv_all.setSelected(false);
        tv_tab_list.setSelected(false);
        tv_tab_shopcar.setSelected(false);
        tv_tab_mine.setSelected(false);

        iv_all.setBackgroundResource(R.drawable.tab_product_none);
        iv_list.setBackgroundResource(R.drawable.tab_shopcard_none);
        iv_shopcar.setBackgroundResource(R.drawable.tab_order_none);
        iv_mine.setBackgroundResource(R.drawable.tab_my_none);
    }

    /*
    * 根据 ID 来设置 tab bar 按钮的选中状态
    * */
    private void setTabBarSelectedIndex(int index) {
        // 根据ID来设置选中
        switch (index) {
            case 0:
                tv_all.setSelected(true);
                iv_all.setBackgroundResource(R.drawable.tab_product);
                break;
            case 1:
                tv_tab_list.setSelected(true);
                iv_list.setBackgroundResource(R.drawable.tab_shopcard);
                break;
            case 2:
                tv_tab_shopcar.setSelected(true);
                iv_shopcar.setBackgroundResource(R.drawable.tab_order);
                break;
            case 3:
                tv_tab_mine.setSelected(true);
                iv_mine.setBackgroundResource(R.drawable.tab_my);
                break;
            default:
                tv_all.setSelected(true);
                iv_all.setBackgroundResource(R.drawable.tab_product);
                break;
        }
    }


    /*
    * ViewPager change listener
    * */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        resetTabBarSelectState();
        setTabBarSelectedIndex(position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /*
    * viewPager Adapter
    * */
    private class MyAdapter extends FragmentPagerAdapter {
        // 初始化
     public MyAdapter(FragmentManager manager) {
         super(manager);
     }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = new AllFragment();
                    break;
                case 1:
                    fragment = new ListFragment();
                    break;
                case 2:
                    fragment = new ShopcarFragment();
                    break;
                case 3:
                    fragment = new MineFragment();
                    break;
                default:
                    break;
            }
            return fragment;
        }


        @Override
        public int getCount() {
            return 4;
        }
    }

}
