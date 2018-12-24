package com.taopao.rxdialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialog;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.taopao.rxdialog.callback.SingleButtonCallback;

/**
 * @Author：淘跑
 * @Date: 2018/12/24 15:39
 * @Use：
 */
public class MessageDialog extends AppCompatDialog implements View.OnClickListener {
    private static Builder mBuilder;
    private TextView mTitleView;
    private TextView mMessageView;
    private TextView mCancelView;
    private View mLineView;
    private TextView mConfirmView;
    private LinearLayout mMessageRootView;

    private MessageDialog(Context context, int theme) {
        super(context, mBuilder.mTheme);
        init(context, theme);
    }

    private void init(Context context, int theme) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.dialog_message, null);
        setContentView(contentView);
        mTitleView = contentView.findViewById(R.id.tv_dialog_message_title);
        mMessageView = contentView.findViewById(R.id.tv_dialog_message_message);
        mCancelView = contentView.findViewById(R.id.tv_dialog_message_cancel);
        mLineView = contentView.findViewById(R.id.v_dialog_message_line);
        mConfirmView = contentView.findViewById(R.id.tv_dialog_message_confirm);
        mMessageRootView = contentView.findViewById(R.id.ll_root);
        apply();
    }

    private void apply() {
        mTitleView.setText(mBuilder.mTitle);
        mMessageView.setText(mBuilder.mMessage);
        mCancelView.setText(mBuilder.mNeutralButtonText);
        mConfirmView.setText(mBuilder.mPositiveButtonText);
        mCancelView.setOnClickListener(this);
        mConfirmView.setOnClickListener(this);
        if (mBuilder.mNeutralButtonText == null || mBuilder.mPositiveButtonText == null) {
            //显示有一个按钮的
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_dialog_message_cancel:
                if (mBuilder.onNeutralCallback != null) {
                    mBuilder.onNeutralCallback.onClick(this);
                }
                break;
            case R.id.tv_dialog_message_confirm:
                if (mBuilder.onPositiveCallback != null) {
                    mBuilder.onPositiveCallback.onClick(this);
                }
                break;
        }
    }

    public static Builder Builder(Context context) {
        mBuilder = new Builder(context);
        return mBuilder;
    }

    public static Builder Builder(Context context, int theme) {
        mBuilder = new Builder(context, theme);
        return mBuilder;
    }


    public static class Builder {
        private int mTheme;//主题
        private Context mContext;
        private boolean mCancelable;
        private CharSequence mTitle;
        private OnCancelListener mOnCancelListener;
        private OnDismissListener mOnDismissListener;
        private OnKeyListener mOnKeyListener;
        private CharSequence mPositiveButtonText;
        private CharSequence mNeutralButtonText;
        private CharSequence mMessage;
        private int mAnimations;
        private MessageButtonCallback onPositiveCallback;
        private MessageButtonCallback onNeutralCallback;


        public Builder(@NonNull Context context) {
//            this(context, resolveDialogTheme(context, 0));
            this(context, R.style.BaseDialogStyle);
        }

        public Builder(@NonNull Context context, @StyleRes int themeResId) {
            this.mContext = context;
            this.mTheme = themeResId;
            this.mCancelable = true;
            this.mAnimations = AnimStyle.DEFAULT;
            mBuilder = this;
        }

        /**
         * 设置标题
         *
         * @param titleId
         * @return
         */
        public Builder title(@StringRes int titleId) {
            this.mTitle = getContext().getText(titleId);
            return mBuilder;
        }

        /**
         * 设置标题
         *
         * @param title
         * @return
         */
        public Builder title(@Nullable CharSequence title) {
            this.mTitle = title;
            return mBuilder;
        }

        public Builder message(@StringRes int messageId) {
            this.mMessage = this.getContext().getText(messageId);
            return mBuilder;
        }

        public Builder message(@Nullable CharSequence message) {
            this.mMessage = message;
            return mBuilder;
        }

        public Builder positiveText(@Nullable CharSequence positiveButtonText) {
            mPositiveButtonText = positiveButtonText;
            return mBuilder;
        }

        public Builder positiveText(@StringRes int positiveid) {
            mPositiveButtonText = getContext().getText(positiveid);
            return mBuilder;
        }

        public Builder negativeText(@Nullable CharSequence neutralButtonText) {
            mNeutralButtonText = neutralButtonText;
            return mBuilder;
        }

        public Builder negativeText(@StringRes int neutralid) {
            mNeutralButtonText = getContext().getText(neutralid);
            return mBuilder;
        }

        /**
         * 点击外部是否不消失
         *
         * @param cancelable
         */
        public Builder setCancelable(boolean cancelable) {
            mCancelable = cancelable;
            return mBuilder;
        }

        /**
         * 点击外部消失监听
         *
         * @param onCancelListener
         */
        public Builder setOnCancelListener(OnCancelListener onCancelListener) {
            mOnCancelListener = onCancelListener;
            return mBuilder;
        }

        /**
         * 消失监听
         *
         * @param onDismissListener
         */
        public Builder setOnDismissListener(OnDismissListener onDismissListener) {
            mOnDismissListener = onDismissListener;
            return mBuilder;
        }

        public Builder setOnKeyListener(OnKeyListener onKeyListener) {
            mOnKeyListener = onKeyListener;
            return mBuilder;
        }

        /**
         * 设置动画，已经封装好几种样式，具体可见{@link AnimStyle}类
         */
        public Builder setAnimStyle(int resId) {
            this.mAnimations = resId;
            return mBuilder;
        }


        public Builder onPositive(@NonNull MessageButtonCallback callback) {
            this.onPositiveCallback = callback;
            return this;
        }


        public Builder onNeutral(@NonNull MessageButtonCallback callback) {
            this.onNeutralCallback = callback;
            return this;
        }

        public MessageDialog create() {
            MessageDialog dialog = new MessageDialog(this.mContext, this.mTheme);
            dialog.setCancelable(this.mCancelable);
            if (this.mCancelable) {
                dialog.setCanceledOnTouchOutside(true);
            }
            dialog.setOnCancelListener(this.mOnCancelListener);
            dialog.setOnDismissListener(this.mOnDismissListener);
            if (this.mOnKeyListener != null) {
                dialog.setOnKeyListener(this.mOnKeyListener);
            }

            // 设置参数
            WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
            params.windowAnimations = this.mAnimations;
            dialog.getWindow().setAttributes(params);

            return dialog;
        }

        public MessageDialog show() {
            MessageDialog dialog = this.create();
            dialog.show();
            return dialog;
        }

        @NonNull
        private Context getContext() {
            return this.mContext;
        }
    }

    public interface MessageButtonCallback {
        void onClick(@NonNull Dialog dialog);
    }

    static int resolveDialogTheme(@NonNull Context context, @StyleRes int resid) {
        if ((resid >>> 24 & 255) >= 1) {
            return resid;
        } else {
            TypedValue outValue = new TypedValue();
            context.getTheme().resolveAttribute(android.support.v7.appcompat.R.attr.alertDialogTheme, outValue, true);
            return outValue.resourceId;
        }
    }
}
