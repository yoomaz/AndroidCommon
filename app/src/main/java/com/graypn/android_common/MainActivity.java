package com.graypn.android_common;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.graypn.cmmon.assist.update.UpdateService;
import com.graypn.cmmon.base.ui.activity.BaseActivity;
import com.graypn.cmmon.net.NetManager;
import com.graypn.cmmon.net.okhttp.DownloadCallBack;
import com.graypn.cmmon.permission.PermissionHelper;
import com.graypn.cmmon.permission.permissionmaster.listener.PermissionListener;
import com.graypn.cmmon.permission.permissionmaster.model.PermissionDeniedResponse;
import com.graypn.cmmon.permission.permissionmaster.model.PermissionGrantedResponse;
import com.graypn.cmmon.utils.IniParseUtils;
import com.graypn.cmmon.utils.NoticeUtils;
import com.graypn.cmmon.utils.StringUtils;
import com.graypn.cmmon.utils.ToastUtils;
import com.graypn.cmmon.utils.VibrateUtils;
import com.graypn.cmmon.utils.WebViewUtils;
import com.graypn.cmmon.view.dialog.CommonDialog;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends BaseActivity {

    Button btn;

    private boolean isOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String pkgName = getPackageName();
        Log.i("MainActivity", pkgName);

        NetManager.init(this);

        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                testPermission();
//                HashMap<String, HashMap<String, String>> hashMap = IniParseUtils.parseIni(MainActivity.this, "logo.ini");
//                Log.i("a", hashMap.size() + "");
//                Intent intent = new Intent(MainActivity.this, UpdateService.class);
//                if (isOpen) {
//                    stopService(intent);
//                } else {
//                    startService(intent);
//                }


//                NetManager.download("http://download.voicecloud.cn/100IME/iFlyIME_v7.0.4405.apk",
//                        Environment.getExternalStorageDirectory().toString(), "test.apk", new DownloadCallBack() {
//                            @Override
//                            public void onFinish(File file) {
//                                ToastUtils.showToast(MainActivity.this, "ok");
//                            }
//
//                            @Override
//                            public void onProgress(long currentBytes, long totalBytes) {
//                                int progress = (int) ((currentBytes * 100) / totalBytes);
//                                Log.i("progress", "progress" + progress);
//                            }
//
//                            @Override
//                            public void onFailure(String error) {
//                                ToastUtils.showToast(MainActivity.this, error);
//                            }
//                        });

//                List<String> list = new ArrayList<String>();
//                list.add("item01");
//                list.add("item02");
//
//                Dialog dialog = new CommonDialog.ListBuilder(MainActivity.this)
//                        .setTitle("温馨提示")
//                        .setContentList(list, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                ToastUtils.showToast(MainActivity.this, which + "");
//                            }
//                        }).build();
//                dialog.show();


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
                new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        Log.i("MainActivity", "onPermissionGranted");
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Log.i("MainActivity", "onPermissionDenied:" + response.isPermanentlyDenied());
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
