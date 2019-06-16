package com.bwie.test.ui.activity;

import android.annotation.SuppressLint;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bwie.test.R;
import com.bwie.test.ui.fragment.CartFragment;
import com.bwie.test.ui.fragment.OthersFragment;
import com.bwie.test.view.NoScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.pager)
    NoScrollView viewPager;
    @BindView(R.id.tab)
    TabLayout tabLayout;
    private Unbinder bind;
    private List<Fragment> list;
    private String[] title;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //绑定Butterknife
        bind = ButterKnife.bind(this);
        initData();
        viewPager.setOffscreenPageLimit(4);
        viewPager.setNoScroll(true);
        //设置适配器
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return title[position];
            }
        });
        tabLayout.setupWithViewPager(viewPager);//把TabLayout与ViewPager绑定
        tabLayout.getTabAt(2).select();
    }

    /**
     *初始化数据
     */
    private void initData() {
        //标题栏
        title = new String[]{"首页","分类","购物车","详情","我的"};
        list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            if(i==2){
                list.add(new CartFragment());
            }else{
                list.add(new OthersFragment());
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解绑Butterknife
        if(bind!=null){
            bind.unbind();
            bind=null;
        }
    }
}
