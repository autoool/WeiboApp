package com.techidea.weiboapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.techidea.weiboapp.activity.MainActivity;
import com.techidea.weiboapp.api.BoreWeiboApi;
import com.techidea.weiboapp.constants.CommonConstants;
import com.techidea.weiboapp.util.DialogUtils;
import com.techidea.weiboapp.util.ToastUtils;
import com.techidea.weiboapp.util.Logger;


/**
 * Created by Administrator on 2015/7/25.
 */
public class BaseActivity extends Activity {

    protected String TAG;

    protected BaseApplication application;
    protected SharedPreferences sp;
    protected Intent intent;
    protected Dialog progressDialog;

    protected ImageLoader imageLoader;
    protected BoreWeiboApi weiboApi;
    protected Gson gson;

    private MainActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        application = (BaseApplication)getApplication();
        sp = getSharedPreferences(CommonConstants.SP_NAME, MODE_PRIVATE);
        intent = getIntent();
        progressDialog = DialogUtils.createLoadingDialog(this);
        application.addActivity(this);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        imageLoader = ImageLoader.getInstance();
        weiboApi = new BoreWeiboApi(this);
        gson = new Gson();
    }

    protected void intent2Activity(Class<? extends Activity> tarActivity){
        Intent intent = new Intent(this,tarActivity);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        application.removeActivity(this);
    }

    protected void finishActivity(){
        this.finish();
    }

    protected void showToast(String msg){
        ToastUtils.showToast(this, msg, Toast.LENGTH_SHORT);
    }

    protected void showLog(String msg){
        Logger.show(TAG, msg);
    }
}
