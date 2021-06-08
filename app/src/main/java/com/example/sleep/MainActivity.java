package com.example.sleep;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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
    private static final String TAG = "MainActivity";
    private static String[] PERMISIONS = {
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

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
        checkPermission();

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

    public void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //第 1 步: 检查是否有相应的权限
            boolean isAllGranted = checkPermissionAllGranted(PERMISIONS);
            if (isAllGranted) {
                Log.e("err", "所有权限已经授权！");
                return;
            }
            // 一次请求多个权限, 如果其他有权限是已经授予的将会自动忽略掉
            ActivityCompat.requestPermissions(this,
                    PERMISIONS, 1);
        }
    }

    /**
     * 检查是否拥有指定的所有权限
     */
    private boolean checkPermissionAllGranted(String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                // 只要有一个权限没有被授予, 则直接返回 false
                //Log.e("err","权限"+permission+"没有授权");
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            boolean isAllGranted = true;
            // 判断是否所有的权限都已经授予了
            for (int grant : grantResults) {
                if (grant != PackageManager.PERMISSION_GRANTED) {
                    isAllGranted = false;
                    break;
                }
            }
            if (isAllGranted) {
                // 所有的权限都授予了
                Log.e("err", "权限都授权了");
            } else {
                // 弹出对话框告诉用户需要权限的原因, 并引导用户去应用权限管理中手动打开权限按钮
                //容易判断错
                finish();
            }
        }
    }
}