package com.taopao.rxdialog.callback;

import android.view.View;

import com.taopao.rxdialog.RxDialog;

/**
 * @Author：淘跑
 * @Date: 2018/12/21 17:14
 * @Use：
 */
public interface ListCallbackSingleChoice {
    void onSelection(RxDialog dialog, View itemView, int position, CharSequence text);

}
