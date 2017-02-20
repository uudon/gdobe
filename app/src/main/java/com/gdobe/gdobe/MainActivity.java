package com.gdobe.gdobe;

import android.databinding.DataBindingUtil;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.gdobe.gdobe.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        initView();
        //initActionBar();
    }

    private void initActionBar() {
        Toolbar toolbar = mBinding.includeTb.toolbar;
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    private void initView() {
        mBinding.includeTb.flTitleMenu.setOnClickListener(this);
        mBinding.includeTb.ivTitleGank.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fl_title_menu:
                Toast.makeText(this,"open menu",Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_title_gank:
                Toast.makeText(this,"open discover",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
