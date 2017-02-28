package com.gdobe.gdobe.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.gdobe.gdobe.R;
import com.gdobe.gdobe.util.PerfectClickListener;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;


/**
 * Created by Administrator on 2017/2/20.
 */

public abstract class BaseFragment<SV extends ViewDataBinding> extends Fragment{

    protected SV bindingView;
    private boolean mIsVisible = false;
    // 内容布局
    protected RelativeLayout mContainer;
    // 加载中
    private LinearLayout mLlProgressBar;
    // 动画
    private AnimationDrawable mAnimationDrawable;
    // 加载失败
    private LinearLayout mRefresh;
    private CompositeSubscription mCompositeSubscription;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View baseFragment = inflater.inflate(R.layout.fragment_base, null);
        bindingView = DataBindingUtil.inflate(getActivity().getLayoutInflater(), setContentView(), null, false);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        bindingView.getRoot().setLayoutParams(params);
        mContainer = (RelativeLayout) baseFragment.findViewById(R.id.container);
        mContainer.addView(bindingView.getRoot());
        return baseFragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()){
            mIsVisible = true;
            onVisible();
        } else{
            mIsVisible = false;
            onInvisible();
        }
    }

    protected void onInvisible(){};

    protected  void onVisible(){
        loadData();
    }

    protected void loadData(){}

    /**
     * please add layout
     * @return
     */
    public abstract int setContentView();

    protected <T extends View> T getView(int id){
        return (T) getView().findViewById(id);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mLlProgressBar = getView(R.id.ll_progress_bar);
        ImageView img = getView(R.id.img_progress);

        //loading animation
        mAnimationDrawable = (AnimationDrawable) img.getDrawable();

        if(!mAnimationDrawable.isRunning()){
            mAnimationDrawable.start();
        }

        mRefresh = getView(R.id.ll_error_refresh);
        mRefresh.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                showLoading();
                onRefresh();
            }
        });
        bindingView.getRoot().setVisibility(View.GONE);
    }

    protected void onRefresh(){};

    protected void showLoading(){
        if(mLlProgressBar.getVisibility() != View.VISIBLE){
            mLlProgressBar.setVisibility(View.VISIBLE);
        }
        // 开始动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
        if (bindingView.getRoot().getVisibility() != View.GONE) {
            bindingView.getRoot().setVisibility(View.GONE);
        }
        if (mRefresh.getVisibility() != View.GONE) {
            mRefresh.setVisibility(View.GONE);
        }

    }

    /**
     * 加载完成的状态
     */
    protected void showContentView() {
        if (mLlProgressBar.getVisibility() != View.GONE) {
            mLlProgressBar.setVisibility(View.GONE);
        }
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (mRefresh.getVisibility() != View.GONE) {
            mRefresh.setVisibility(View.GONE);
        }
        if (bindingView.getRoot().getVisibility() != View.VISIBLE) {
            bindingView.getRoot().setVisibility(View.VISIBLE);
        }
    }

    /**
     * 加载失败点击重新加载的状态
     */
    protected void showError() {
        if (mLlProgressBar.getVisibility() != View.GONE) {
            mLlProgressBar.setVisibility(View.GONE);
        }
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (mRefresh.getVisibility() != View.VISIBLE) {
            mRefresh.setVisibility(View.VISIBLE);
        }
        if (bindingView.getRoot().getVisibility() != View.GONE) {
            bindingView.getRoot().setVisibility(View.GONE);
        }
    }

    public void addSubscription(Subscription s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(s);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            this.mCompositeSubscription.unsubscribe();
        }
    }

    public void removeSubscription() {
        if (this.mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            this.mCompositeSubscription.unsubscribe();
        }
    }
}
