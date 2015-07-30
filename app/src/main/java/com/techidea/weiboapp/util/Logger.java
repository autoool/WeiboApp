package com.techidea.weiboapp.util;

import android.util.Log;

import com.techidea.weiboapp.constants.CommonConstants;


/**
 * Created by Administrator on 2015/7/25.
 */
public class Logger {

    /**

     */
    public static void show(String TAG,String msg){
        if(!CommonConstants.isShowLog){
            return;
        }
        show(TAG,msg, Log.INFO);
    }

    public static void show(String TAG,String msg,int level){
        if(!CommonConstants.isShowLog){
            return;
        }
        switch(level){

            case Log.VERBOSE:
                Log.v(TAG, msg);
                break;
            case Log.DEBUG:
                Log.d(TAG, msg);
                break;
            case Log.INFO:
                Log.i(TAG, msg);
                break;
            case Log.WARN:
                Log.w(TAG, msg);
                break;
            case Log.ERROR:
                Log.e(TAG, msg);
                break;
            default:
                break;
        }
    }
}
