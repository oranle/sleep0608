package com.example.sleep.ui;

import android.view.View;

import com.example.sleep.R;
import com.example.sleep.databinding.FragHomeBinding;
import com.example.sleep.util.MediaRecorderDemo;
import com.yang.me.lib.BaseBindFragment;

import org.jetbrains.annotations.NotNull;

public class HomeFragment extends BaseBindFragment<FragHomeBinding> {

    private boolean isRecording = false;
    private MediaRecorderDemo mediaRecorderDemo;

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.frag_home;
    }

    @Override
    protected void init() {
        updateButton();
        mViewBinding.startAndStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isRecording) {
                    getRecorder().startRecord();
                } else {
                    getRecorder().stopRecord();
                }
                isRecording = !isRecording;
                updateButton();
            }
        });
    }

    @NotNull
    private MediaRecorderDemo getRecorder() {
        if(mediaRecorderDemo == null) {
            mediaRecorderDemo = new MediaRecorderDemo();
        }
        return mediaRecorderDemo;
    }

    private void updateButton() {
        mViewBinding.startAndStop.setText(isRecording ? "停止" : "开始");
        mViewBinding.startAndStop.setBackgroundResource(
                isRecording ? R.drawable.shape_bg_red_btn : R.drawable.shape_login_btn);
    }
}
