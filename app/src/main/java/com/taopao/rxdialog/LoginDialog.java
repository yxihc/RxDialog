package com.taopao.rxdialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * @Author：淘跑
 * @Date: 2019/1/7 09:27
 * @Use：
 */
public class LoginDialog extends DialogBase {
    private View mView1;
    private View mView2;
    private View mLl_root;

    LoginDialog(Context context, int theme) {
        super(context, theme);
        init(context);
    }

    private Context mContext;

    private void init(final Context context) {
        mContext = context;
        mRootView = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
        setViewInternal(mRootView);

        mView1 = findViewById(R.id.view1);
        mView2 = findViewById(R.id.view2);
        mLl_root = findViewById(R.id.root);

        mView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView1.animate().scaleX(0.5f)
                        .scaleY(0.5f)
                        .setDuration(500)
                        .setInterpolator(new AccelerateDecelerateInterpolator())
                        .start();
                mHandler.sendEmptyMessageDelayed(1, 500);
            }
        });
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
//            mView1.setVisibility(View.GONE);
//            mView2.setVisibility(View.VISIBLE);
//            mView1.setBackgroundResource(R.color.dialog_cancel);
            mView1.setVisibility(View.INVISIBLE);
            mView2.setVisibility(View.VISIBLE);

        }
    };


}
