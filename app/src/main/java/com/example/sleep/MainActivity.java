package com.example.sleep;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.sleep.databinding.ActivityMainBinding;
import com.example.sleep.ui.HomeFragment;
import com.example.sleep.ui.NotificationFragment;
import com.example.sleep.ui.TabFragmentPagerAdapter;
import com.example.sleep.ui.login.PersonalFragment;
import com.yang.me.lib.BaseBindActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseBindActivity<ActivityMainBinding> {
    private List<Fragment> mFragments;
    private String[] mTitles = {"首页", "通知", "我的"};
    private int[] mImages = {
            R.drawable.selecor_btn_home,
            R.drawable.selecor_btn_notification,
            R.drawable.selecor_btn_mine};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        initFragment();
        //实例化 FragmentPagerAdapter 并将 Fragment 列表传入
        TabFragmentPagerAdapter adapter = new TabFragmentPagerAdapter(getSupportFragmentManager(), mFragments, mTitles);

        //将实例化好的 Adapter 设置到 ViewPager 中
        mViewBinding.tabViewPager.setAdapter(adapter);

        //设置打开时的默认页面
        mViewBinding.tabViewPager.setCurrentItem(0);

        //将 ViewPager 绑定到 TabLayout上
        mViewBinding.tabLayout.setupWithViewPager(mViewBinding.tabViewPager);

        //进行 Tab自定义布局的实例化和添加
        for (int i = 0; i < mTitles.length; i++) {
            //实例化 Tab 布局
            View view = LayoutInflater.from(this).inflate(R.layout.item_tab, null);
            TextView tabTV = view.findViewById(R.id.tab);
            ImageView icon = view.findViewById(R.id.iv);
            tabTV.setText(mTitles[i]);
            icon.setImageResource(mImages[i]);
            //将实例化好的 Tab 布局设置给当前的 Tab即可
            mViewBinding.tabLayout.getTabAt(i).setCustomView(view);
        }
    }


    private void initFragment() {
        mFragments = new ArrayList<>();

        mFragments.add(new HomeFragment());
        mFragments.add(new NotificationFragment());
        mFragments.add(new PersonalFragment());
    }

}