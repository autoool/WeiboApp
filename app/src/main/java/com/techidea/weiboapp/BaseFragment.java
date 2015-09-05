package com.techidea.weiboapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.techidea.weiboapp.activity.MainActivity;
import com.techidea.weiboapp.api.BoreWeiboApi;
import com.techidea.weiboapp.util.DialogUtils;

/**
 * Created by Administrator on 2015/7/25.
 */
public class BaseFragment extends Fragment {

    protected MainActivity activity;
    protected Dialog progressDialog;

    protected ImageLoader imageLoader;
    protected BoreWeiboApi weiboApi;
    protected Gson gson;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        activity = (MainActivity)getActivity();
        progressDialog = DialogUtils.createLoadingDialog(activity);

        imageLoader = ImageLoader.getInstance();
        weiboApi = new BoreWeiboApi(activity);
        gson = new Gson();
    }

    protected void intent2Activity(Class<? extends Activity> tarActivity){
        Intent intent = new Intent(activity,tarActivity);
        startActivity(intent);
    }
}
