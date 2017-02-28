package com.gdobe.gdobe.ui.home;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gdobe.gdobe.R;
import com.gdobe.gdobe.base.BaseFragment;
import com.gdobe.gdobe.databinding.FragmentEverydayBinding;
import com.gdobe.gdobe.util.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/2/21.
 */

public class EverydayFragment extends BaseFragment<FragmentEverydayBinding>{
    private String TAG = getClass().getName();
    @Override
    public int setContentView() {
        return R.layout.fragment_everyday;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        showContentView();
    }

    @Override
    protected void loadData() {
        ArrayList<String> bitmaps = new ArrayList<String>();
        bitmaps.add("https://static.pexels.com/photos/6789/flowers-petals-gift-flower-medium.jpg");;
        bitmaps.add("https://static.pexels.com/photos/7116/mountains-water-trees-lake-medium.jpg");

        bindingView.banner.setImages(bitmaps).setImageLoader(new GlideImageLoader()).start();
    }
}
