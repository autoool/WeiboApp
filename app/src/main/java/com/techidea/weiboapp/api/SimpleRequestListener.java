package com.techidea.weiboapp.api;

/**
 * Created by Administrator on 2015/7/30.
 */

import android.app.Dialog;
import android.content.Context;
import android.widget.Toast;

import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.techidea.weiboapp.util.Logger;
import com.techidea.weiboapp.util.ToastUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class SimpleRequestListener implements RequestListener {

    private Context context;
    private Dialog progressDialog;

    public SimpleRequestListener(Context context,Dialog progressDialog) {
        this.context = context;
        this.progressDialog = progressDialog;
    }

    public  void onComplete(String response){
        onAllDone();
        Logger.show("Request onComplete",response);
    }

    public void onComplete4binary(ByteArrayOutputStream responseOS){
        onAllDone();
        Logger.show("Request onComplete4binary", responseOS.size() + "");
    }

    public void onIOException(IOException e){
        onAllDone();
        ToastUtils.showToast(context, e.getMessage(), Toast.LENGTH_SHORT);
        Logger.show("Request onIOException",e.toString());
    }

    public void onError(WeiboException e){
        onAllDone();
        ToastUtils.showToast(context, e.getMessage(), Toast.LENGTH_SHORT);
        Logger.show("Request onError", e.toString());
    }

    public void onAllDone()
    {
        if(progressDialog != null){
            progressDialog.dismiss();
        }
    }
}
