package com.taopao.rxdialog.callback;

import android.view.View;

import com.taopao.rxdialog.RxDialog;

/**
 * @Author：淘跑
 * @Date: 2018/12/21 17:13
 * @Use：
 */
public interface ListLongCallback {
    boolean onLongSelection(RxDialog dialog, View itemView, int position, CharSequence text);

}
