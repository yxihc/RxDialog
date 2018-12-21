package com.taopao.rxdialog.callback;

import com.taopao.rxdialog.RxDialog;

/**
 * @Author：淘跑
 * @Date: 2018/12/21 17:15
 * @Use：
 */
public interface ListCallbackMultiChoice {
    /**
     * Return true to allow the check box to be checked, if the alwaysCallSingleChoice() option is
     * used.
     *
     * @param dialog The dialog of which a list item was selected.
     * @param which  The indices of the items that were selected.
     * @param text   The text of the items that were selected.
     * @return True to allow the checkbox to be selected.
     */
    boolean onSelection(RxDialog dialog, Integer[] which, CharSequence[] text);
}
