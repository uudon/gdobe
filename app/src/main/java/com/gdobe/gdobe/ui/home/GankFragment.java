package com.gdobe.gdobe.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.gdobe.gdobe.R;
import com.gdobe.gdobe.base.BaseFragment;
import com.gdobe.gdobe.databinding.FragmentGankBinding;
import com.gdobe.gdobe.http.rx.RxBus;
import com.gdobe.gdobe.http.rx.RxCodeConstants;
import com.gdobe.gdobe.view.MyFragmentPagerAdapter;

import java.util.ArrayList;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/2/21.
 */

public class GankFragment extends BaseFragment<FragmentGankBinding>{

    private ArrayList<String> mTitleList = new ArrayList<>(4);
    private ArrayList<Fragment> mFragments = new ArrayList<>(4);
    @Override
    public int setContentView() {
        return R.layout.fragment_gank;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        showLoading();
        initFragmentList();
        MyFragmentPagerAdapter myAdapter = new MyFragmentPagerAdapter(getChildFragmentManager(), mFragments, mTitleList);
        bindingView.vpGank.setAdapter(myAdapter);
        bindingView.vpGank.setOffscreenPageLimit(3);
        myAdapter.notifyDataSetChanged();
        bindingView.tabGank.setTabMode(TabLayout.MODE_FIXED);
        bindingView.tabGank.setupWithViewPager(bindingView.vpGank);
        showContentView();
        //initRxBus();
    }

    private void initFragmentList() {
        mTitleList.add("每日推荐");
        mTitleList.add("福利");
        mFragments.add(new EverydayFragment());
        mFragments.add(new WelfareFragment());
    }
/*
    *//**
     * 每日推荐点击"更多"跳转
     *//*
    private void initRxBus() {
        Subscription subscription = RxBus.getDefault().toObservable(RxCodeConstants.JUMP_TYPE, Integer.class)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        if (integer == 0) {
                            bindingView.vpGank.setCurrentItem(3);
                        } else if (integer == 1) {
                            bindingView.vpGank.setCurrentItem(1);
                        } else if (integer == 2) {
                            bindingView.vpGank.setCurrentItem(2);
                        }
                    }
                });
        addSubscription(subscription);
    }*/
}
