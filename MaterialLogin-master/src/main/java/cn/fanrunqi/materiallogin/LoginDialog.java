package cn.fanrunqi.materiallogin;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;

/**
 * @Author：淘跑
 * @Date: 2019/1/7 09:27
 * @Use：
 */
public class LoginDialog extends Dialog {
    private View mRootView;
    private CardView mCv;
    private CardView mCv_loading;
    private Button mBt_go;

    LoginDialog(Context context) {
        super(context, R.style.BaseDialogStyle);
        init(context);
    }

    private Context mContext;

    private void init(final Context context) {
        mContext = context;
        mRootView = LayoutInflater.from(context).inflate(R.layout.dialog_login, null);
        setContentView(mRootView);

        mCv = mRootView.findViewById(R.id.cv);
        mCv_loading = mRootView.findViewById(R.id.cv_loading);

        mBt_go = mRootView.findViewById(R.id.bt_go);

        mBt_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCv.animate().scaleX(0.7f)
                        .scaleY(0.7f)
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
            mCv.setVisibility(View.INVISIBLE);
            mCv_loading.setVisibility(View.VISIBLE);

        }
    };


}
