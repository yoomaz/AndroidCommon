package com.graypn.android_common;

import android.Manifest;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.graypn.android_common.web.WebViewUtils;
import com.graypn.cmmon.base.ui.activity.BaseActivity;
import com.graypn.cmmon.net.NetManager;
import com.graypn.cmmon.permission.PermissionHelper;
import com.graypn.cmmon.system.user.ContactHelper;
import com.graypn.cmmon.system.user.bean.CallRecord;
import com.graypn.cmmon.system.user.bean.ContactInfo;
import com.graypn.cmmon.system.user.bean.SmsInfo;
import com.graypn.cmmon.utils.PackageUtil;
import com.graypn.permissionmaster.listener.PermissionListener;
import com.graypn.permissionmaster.model.PermissionDeniedResponse;
import com.graypn.permissionmaster.model.PermissionGrantedResponse;

import java.io.File;
import java.util.List;


public class MainActivity extends BaseActivity {

    Button btn;
    ImageView img;

    private boolean isOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String pkgName = getPackageName();
        Log.i("MainActivity", pkgName);

        NetManager.init(this);

        btn = (Button) findViewById(R.id.btn);
        img = (ImageView) findViewById(R.id.img);
//        Glide.with(this).load(R.drawable.ic_gif).into(new GlideDrawableImageViewTarget(img, 1));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                NetworkUtils.testCurrentNetInfo(MainActivity.this);

//                testCountDownTimer();

//                testInstall();

//                testPermission();

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
//                                PackageUtil.install(MainActivity.this, file);
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

                List<ContactInfo> list = ContactHelper.getContacts(getApplicationContext());

                List<CallRecord> list2 = ContactHelper.getCallLogs(getApplicationContext());

                List<SmsInfo> list3 = ContactHelper.getSmsInfo(getApplicationContext());
                Log.i("taggg", "onClick: ");


            }
        });
    }



    private void testCountDownTimer() {
        CountDownTimer timer = new CountDownTimer(30000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                Log.i("MainActivity", "seconds remaining: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                Log.i("MainActivity", "onFinish");
            }
        };
        timer.start();
    }


    // 测试安装Apk
    private void testInstall() {
        String filePath = PackageUtil.getSDCardPath() + "/" + "szssmartparty.apk";
        File file = new File(filePath);

        if (file.exists()) {
            PackageUtil.install(this, file);
        }
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
