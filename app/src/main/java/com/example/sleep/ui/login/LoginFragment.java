package com.example.sleep.ui.login;

import android.view.View;
import android.view.ViewGroup;

import com.example.sleep.R;
import com.example.sleep.databinding.FragLoginAccountBinding;
import com.yang.me.lib.BaseBindFragment;
import com.yang.me.lib.FragmentDisplayActivity;
import com.yang.me.lib.util.Util;
import com.yang.me.lib.util.SizeUtils;


/**
 * <pre>
 *
 * Description: 登录界面
 *
 * Author: xucunyang
 * Time: 2021/5/11 14:45
 *
 * </pre>
 */
public class LoginFragment extends BaseBindFragment<FragLoginAccountBinding> {
    @Override
    protected int getFragmentLayoutId() {
        return R.layout.frag_login_account;
    }

    @Override
    protected void init() {
        setImmersiveStatusBar(mViewBinding.includeTopView.toolBar);

        int height = (int) (SizeUtils.dp2px(getContext(), 132) + Util.getStatusBarHeight(getResources()));
        Util.setViewLayoutParams(mViewBinding.includeTopView.topBg, ViewGroup.LayoutParams.MATCH_PARENT, height);
        int topLayoutheight = (int) (SizeUtils.dp2px(getContext(), 180) + Util.getStatusBarHeight(getResources()));
        Util.setViewLayoutParams(mViewBinding.includeTopView.topLayout, ViewGroup.LayoutParams.MATCH_PARENT, topLayoutheight);

        mViewBinding.includeTopView.closeBtn.setOnClickListener(this);
        mViewBinding.quickLoginBtn.setOnClickListener(this);
        mViewBinding.loginBtn.setOnClickListener(this);

        mViewBinding.registerNow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v == mViewBinding.includeTopView.closeBtn) {
            onBackPress();
        } else if (v == mViewBinding.loginBtn) {

        } else if (v == mViewBinding.registerNow) {
            FragmentDisplayActivity.start(getContext(), RegisterFragment.class, null, false);
        }
    }
}
