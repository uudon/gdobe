package com.gdobe.gdobe.ui.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.gdobe.gdobe.R;
import com.gdobe.gdobe.base.BaseFragment;
import com.gdobe.gdobe.databinding.FragmentHomeBinding;
import com.gdobe.gdobe.view.MyFragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/2/23.
 */

public class HomeFragment extends BaseFragment<FragmentHomeBinding>{
    ArrayList<Fragment> mFragmentList = new ArrayList<>();
    ArrayList<String> mFragmentTitle = new ArrayList<>();
    private ViewPager mVpHome;
    private TabLayout mTbHome;
    @Override
    public int setContentView() {
        return R.layout.fragment_home;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initFragmentList();
    }

    private void initView() {
        mVpHome = bindingView.vpHome;
        mTbHome = bindingView.tbHome;
    }

    private void initFragmentList() {
        mFragmentList.add(new EverydayFragment());
        mFragmentList.add(new WelfareFragment());
        mFragmentTitle.add("每日推荐");
        mFragmentTitle.add("福利");

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getChildFragmentManager(),mFragmentList,mFragmentTitle);
        mVpHome.setAdapter(adapter);
        mVpHome.setOffscreenPageLimit(3);

        mTbHome.setTabMode(TabLayout.MODE_FIXED);
        mTbHome.setupWithViewPager(mVpHome);//链接tablayout与viewpage

        adapter.notifyDataSetChanged();

        showContentView();
    }

}
