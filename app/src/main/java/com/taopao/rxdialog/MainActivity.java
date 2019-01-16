package com.taopao.rxdialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this, R.style.CustomDialog)
                        .setView(R.layout.dialog_test)
                        .setPositiveButton("", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();

            }
        });
        findViewById(R.id.view1).setOnClickListener(this);
    }


    public void click2(View view) {
        LoginDialog loginDialog = new LoginDialog(this, R.style.BaseDialogStyle);
        loginDialog.show();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view1:
                MessageDialog.Builder(this)
                        .title("sdasds")
                        .message("sdsads")
                        .negativeText("qdsad")
                        .positiveText("kasjdksad")
                        .setAnimStyle(AnimStyle.IOS)
                        .onPositive(new MessageDialog.MessageButtonCallback() {
                            @Override
                            public void onClick(@NonNull Dialog dialog) {
                                dialog.dismiss();
                            }
                        })
                        .onNegative(new MessageDialog.MessageButtonCallback() {
                            @Override
                            public void onClick(@NonNull Dialog dialog) {

                            }
                        })
                        .show();
                break;
        }
    }
}