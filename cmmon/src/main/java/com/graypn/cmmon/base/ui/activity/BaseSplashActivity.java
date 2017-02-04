package com.graypn.cmmon.base.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;

/**
 * Created by Wzs on 2016/6/8.
 */
public class BaseSplashActivity extends BaseActivity {

    private ProgressDialog updateProgressDialog;
    private final int REQUEST_CODE_STORAGE = 1;
    private boolean isGranted = false;

    @Override
    protected void initVariables(Bundle savedInstanceState) {
    }

    @Override
    protected void initViews() {
//        setContentView(R.layout.activity_splash);
//        initProgressDialog();
    }

    @Override
    protected void initData() {

        //请求网络接口，判断是否为最新版，根据结果决定是否更新
//        checkVersion();
//        startActivity(new Intent(this, NavigationActivity.class));
    }

    private void checkVersion() {
//        if (NetUtils.isNetworkAvailable(this) != 0) {
//            NetManager.getDataAsync(GlobleNetApi.getUpdateUrl(), new Callback() {
//                @Override
//                public void onFailure(Request request, IOException e) {
//                    enterHome();
//                }
//
//                @Override
//                public void onResponse(Response response) throws IOException {
//                    String result = response.body().string();
//                    if (!TextUtils.isEmpty(result)) {
//                        processData(result);
//                    }
//                }
//            });
//
//        } else {
//            enterHome();
//        }
    }
//
//    private void processData(String result) {
//        final UpdateInfo updateInfo = GsonUtils.changeJsonToBean(result, UpdateInfo.class);
//        if (PackageUtil.getAppVersionName(getApplicationContext()).equals(updateInfo.version)) {
//            try {
//                Thread.sleep(400);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            enterHome();
//            finish();
//        } else {
//            // update apk
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    int isReadGrantedPermission = ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
//                    int isWriteGrantedPermission = ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//                    if (isReadGrantedPermission != PackageManager.PERMISSION_GRANTED || isWriteGrantedPermission != PackageManager.PERMISSION_GRANTED) {
//                        if (Build.VERSION.SDK_INT >= 23) {
//                            ActivityCompat.requestPermissions(SplashActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_STORAGE);
//                        }
//                    } else {
//                        isGranted = true;
//                        showUpdateDialog(updateInfo);
//                    }
//                }
//            });
//        }
//    }
//
//    private void showUpdateDialog(final UpdateInfo updateInfo) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("有新版本,快来更新吧");
//        builder.setMessage(updateInfo.description);
//        builder.setCancelable(false);
//        builder.setPositiveButton("更新", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                downloadApk(updateInfo);
//            }
//        });
//        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                enterHome();
//            }
//        });
//        builder.show();
//    }
//
//    private void downloadApk(UpdateInfo updateInfo) {
//        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && isGranted) {
//            FinalHttp finalHttp = new FinalHttp();
//            finalHttp.download(updateInfo.apkurl, Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + updateInfo.apkname, new AjaxCallBack<File>() {
//                @Override
//                public void onFailure(Throwable t, int errorNo, String strMsg) {
//                    t.printStackTrace();
//                    Toast.makeText(getApplicationContext(), strMsg, Toast.LENGTH_SHORT).show();
//                    enterHome();
//                    super.onFailure(t, errorNo, strMsg);
//                }
//
//                @Override
//                public void onLoading(long count, long current) {
//                    super.onLoading(count, current);
//                    int progress = (int) (current * 100 / count);
//                    updateProgressDialog.show();
//                    updateProgressDialog.setProgress(progress);
//                }
//
//                @Override
//                public void onSuccess(File file) {
//                    super.onSuccess(file);
//                    updateProgressDialog.dismiss();
//                    if (PackageUtil.install(getApplicationContext(), file)) {
//                        finish();
//                    }
//                }
//            });
//        }
//    }
//
//    private void enterHome() {
//        Intent intent = new Intent(this, NavigationActivity.class);
//        startActivity(intent);
//        finish();
//
//    }
//
//    private void initProgressDialog() {
//        updateProgressDialog = new ProgressDialog(this);
//        updateProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//        updateProgressDialog.setCancelable(false);
//        updateProgressDialog.setCanceledOnTouchOutside(false);
//        updateProgressDialog.setTitle("正在下载中，请稍后");
//        updateProgressDialog.setMax(100);
//    }

}
