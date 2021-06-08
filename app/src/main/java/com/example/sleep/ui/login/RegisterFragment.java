package com.example.sleep.ui.login;

import android.view.View;
import android.view.ViewGroup;

import com.example.sleep.R;
import com.example.sleep.databinding.FragRegisterAccountBinding;
import com.yang.me.lib.BaseBindFragment;
import com.yang.me.lib.util.SizeUtils;
import com.yang.me.lib.util.Util;


public class RegisterFragment extends BaseBindFragment<FragRegisterAccountBinding> {
    @Override
    protected int getFragmentLayoutId() {
        return R.layout.frag_register_account;
    }

    @Override
    protected void init() {
        setImmersiveStatusBar(mViewBinding.includeTopView.toolBar);
        int height = (int) (SizeUtils.dp2px(getContext(), 132) + Util.getStatusBarHeight(getResources()));
        Util.setViewLayoutParams(mViewBinding.includeTopView.topBg, ViewGroup.LayoutParams.MATCH_PARENT, height);
        int topLayoutheight = (int) (SizeUtils.dp2px(getContext(), 180) + Util.getStatusBarHeight(getResources()));
        Util.setViewLayoutParams(mViewBinding.includeTopView.topLayout, ViewGroup.LayoutParams.MATCH_PARENT, topLayoutheight);

        mViewBinding.includeTopView.title.setText("账号注册");

        mViewBinding.completeInfo.setOnClickListener(this);
        mViewBinding.submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v == mViewBinding.submit) {
            mViewBinding.registerLayout.setVisibility(View.GONE);
            mViewBinding.registerResultLayout.setVisibility(View.VISIBLE);
        } else if (v == mViewBinding.completeInfo) {
        } else if (v == mViewBinding.avoidAndLogin) {

        }
    }
}
