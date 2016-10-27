package com.wydewy.medicalapp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.wydewy.medicalapp.databinding.ActivityMainBinding;
import com.wydewy.medicalapp.fragment.HomeFragment;
import com.wydewy.medicalapp.fragment.ProfileFragment;

public class MainActivity extends BaseActivity {
    ActivityMainBinding binding;
    private FragmentManager fManager;
    private HomeFragment homeFragment;
    private ProfileFragment profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binding.toolbar);
        binding.content.bottom.setEventHandler(new EventHandler());
        fManager = getSupportFragmentManager();
        setSelect(0);
    }

    /**
     * 选择某个页面
     * @param i 页面序号
     */
    private void setSelect(int i) {
        FragmentTransaction transaction = fManager.beginTransaction();
        hideFragment(transaction);
        switch (i) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.id_content, homeFragment);
                } else {
                    transaction.show(homeFragment);
                }
                break;
            case 1:
                if (profileFragment == null) {
                    profileFragment = new ProfileFragment();
                    transaction.add(R.id.id_content, profileFragment);
                } else {
                    transaction.show(profileFragment);
                }
                break;
        }


        transaction.commit();
    }

    /**
     * 隐藏所有页面
     * @param transaction
     */
    private void hideFragment(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (profileFragment != null) {
            transaction.hide(profileFragment);
        }
    }

    /**
     * 处理点击事件
     */
    public class EventHandler {
        public void handleClick(View view) {
            switch (view.getId()) {
                case R.id.id_tab1:
                    setSelect(0);
                    break;
                case R.id.id_tab2:
                    setSelect(1);
                    break;

            }
        }
    }

}
