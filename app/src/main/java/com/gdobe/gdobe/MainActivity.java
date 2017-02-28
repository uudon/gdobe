package com.gdobe.gdobe;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import com.gdobe.gdobe.databinding.ActivityMainBinding;
import com.gdobe.gdobe.ui.friends.FriendsFragment;
import com.gdobe.gdobe.ui.home.HomeFragment;
import com.gdobe.gdobe.ui.music.MusicFragment;
import com.gdobe.gdobe.util.CommonUtils;
import com.gdobe.gdobe.view.MyFragmentPagerAdapter;
import com.gdobe.gdobe.view.statusbar.StatusBarUtil;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/2/21.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = getClass().getName();

    private ViewPager mVp;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActivityMainBinding binding;
    private ImageView mIvDiscover, mIvMusic, mIvFriends;
    private static final int PAGE_INDEX_HOME = 0, PAGE_INDEX_MUSIC = 1, PAGE_INDEX_FRIENDS = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initView();

        initToolBar();

        StatusBarUtil.setColorNoTranslucentForDrawerLayout(MainActivity.this, binding.drawerLayout, CommonUtils.getColor(R.color.colorPrimary));

        initContentFragment();
        binding.ivToolbarMenu.setOnClickListener(this);
        mIvDiscover.setOnClickListener(this);
        mIvMusic.setOnClickListener(this);
        mIvFriends.setOnClickListener(this);
    }

    private void initView() {
        mToolbar = binding.toolbar;
        mVp = binding.vpContent;
        mDrawerLayout = binding.drawerLayout;
        mIvDiscover = binding.ivDiscover;
        mIvMusic = binding.ivMusic;
        mIvFriends = binding.ivFriend;
    }

    private void initContentFragment() {
        ArrayList<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new MusicFragment());
        fragmentList.add(new FriendsFragment());

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList);
        mVp.setAdapter(adapter);
        // 设置ViewPager最大缓存的页面个数(cpu消耗少)
        mVp.setOffscreenPageLimit(2);
        mIvDiscover.setSelected(true);//设置默认的选择
        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setSelectStatus(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initToolBar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_toolbar_menu:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.iv_discover:
                setSelectStatus(PAGE_INDEX_HOME);
                mVp.setCurrentItem(PAGE_INDEX_HOME);
                break;
            case R.id.iv_music:
                setSelectStatus(PAGE_INDEX_MUSIC);
                mVp.setCurrentItem(PAGE_INDEX_MUSIC);
                break;
            case R.id.iv_friend:
                setSelectStatus(PAGE_INDEX_FRIENDS);
                mVp.setCurrentItem(PAGE_INDEX_FRIENDS);
                break;
        }
    }

    private void setSelectStatus(int pageSelected) {
        boolean isDiscoverSelect = false, isMusicSelect = false, isFriendsSelect = false;
        switch (pageSelected) {
            case PAGE_INDEX_HOME:
                isDiscoverSelect = true;
                break;
            case PAGE_INDEX_MUSIC:
                isMusicSelect = true;
                break;
            case PAGE_INDEX_FRIENDS:
                isFriendsSelect = true;
                break;
        }
        mIvDiscover.setSelected(isDiscoverSelect);
        mIvMusic.setSelected(isMusicSelect);
        mIvFriends.setSelected(isFriendsSelect);
    }
}
