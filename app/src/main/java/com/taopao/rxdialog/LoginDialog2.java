package com.taopao.rxdialog;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * @Author：淘跑
 * @Date: 2019/1/7 09:27
 * @Use：
 */
public class LoginDialog2 extends DialogBase {
    private View mView1;
    private View mView2;
    private View mLl_root;

    LoginDialog2(Context context, int theme) {
        super(context, theme);
        init(context);
    }

    private void init(final Context context) {
        mRootView = LayoutInflater.from(context).inflate(R.layout.dialog_loading2, null);
        setViewInternal(mRootView);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
//            mView1.setVisibility(View.GONE);
//            mView2.setVisibility(View.VISIBLE);
//            mView1.setBackgroundResource(R.color.dialog_cancel);
            mView1.setVisibility(View.GONE);
            mView2.setVisibility(View.VISIBLE);
        }
    };


}
