package com.graypn.android_common;

import android.Manifest;
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
import com.graypn.cmmon.utils.VibrateUtils;
import com.graypn.cmmon.utils.WebViewUtils;
import com.wang.avi.AVLoadingIndicatorView;


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

    void tetsWebActivity() {
        //                WebViewUtils.launchWebActivity(MainActivity.this, "baidu", "http://www.baidu.com");
        WebViewUtils.launchWebPopupActivity(MainActivity.this, "http://www.baidu.com");

    }
}
