package com.graypn.android_common;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.graypn.cmmon.base.ui.activity.BaseActivity;
import com.graypn.cmmon.permission.OnPermissionListener;
import com.graypn.cmmon.permission.PermissionHelper;
import com.graypn.cmmon.utils.NoticeUtils;
import com.graypn.cmmon.utils.StringUtils;
import com.graypn.cmmon.utils.ToastUtils;
import com.graypn.cmmon.utils.VibrateUtils;
import com.graypn.cmmon.utils.WebViewUtils;
import com.graypn.cmmon.view.dialog.CommonDialog;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity {

    AVLoadingIndicatorView av;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> list = new ArrayList<String>();
                list.add("item01");
                list.add("item02");

                Dialog dialog = new CommonDialog.ListBuilder(MainActivity.this)
                        .setTitle("温馨提示")
                        .setContentList(list, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ToastUtils.showToast(MainActivity.this, which + "");
                            }
                        }).build();
                dialog.show();


//                Dialog dialog = new CommonDialog.AlertBuilder(MainActivity.this)
//                        .setTitle("温馨提示")
//                        .setMessage("是否卸载该插件？")
//                        .setPositiveButton("卸载", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        })
//                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        })
//                        .build();
//                dialog.show();
            }
        });
    }

    @Override
    protected void initVariables(Bundle savedInstanceState) {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected boolean translucentStatusBar() {
        return true;
    }

    void testPermission() {
        PermissionHelper.requestPermission(MainActivity.this,
                new OnPermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        Log.i("MainActivity", "onPermissionGranted");
                    }

                    @Override
                    public void onPermissionDenied() {
                        Log.i("MainActivity", "onPermissionDenied");
                    }

                    @Override
                    public void onError() {
                        Log.i("MainActivity", "onError");
                    }
                },
                Manifest.permission.CAMERA,
                "请求权限",
                "必须答应");
    }

    void tetsWebActivity() {
        //                WebViewUtils.launchWebActivity(MainActivity.this, "baidu", "http://www.baidu.com");
        WebViewUtils.launchWebPopupActivity(MainActivity.this, "http://www.baidu.com");

    }
}
