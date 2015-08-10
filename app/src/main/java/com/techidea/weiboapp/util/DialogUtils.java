package com.techidea.weiboapp.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.techidea.weiboapp.BuildConfig;
import com.techidea.weiboapp.R;

import java.util.List;

/**
 * Created by Administrator on 2015/8/10.
 */
public class DialogUtils {

    public static Dialog createLoadingDialog(Context context){
        ProgressDialog progressDialog = new ProgressDialog(context, R.style.DialogCommon);
        return progressDialog;
    }

    /**
     * 提示信息dialog
     * @param context
     * @param title
     * @param msg
     * @return
     */
    public static AlertDialog showMsgDialog(Context context,String title,
                                            String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if(!TextUtils.isEmpty(title)){
            builder.setTitle(title);
        }
        AlertDialog dialog = builder.setMessage(msg)
                .setNegativeButton("确定",null).show();

        return dialog;
    }

    /**
     * 确认dialog
     * @param context
     * @param title
     * @param msg
     * @param OnClickListener
     * @return
     */
    public static AlertDialog showConfirmDialog(Context context,String title,
                                                String msg,DialogInterface.OnClickListener onClickListener){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if(!TextUtils.isEmpty(title)){
            builder.setTitle(title);
        }
        AlertDialog dialog =  builder.setMessage(msg)
                .setPositiveButton("确认", onClickListener)
                .setNegativeButton("取消",onClickListener).show();
        return  dialog;
    }

    /**
     * 列表型dialog
     * @param context
     * @param title
     * @param items
     * @param onClickListener
     * @return
     */
    public static AlertDialog showListDialog(Context context,String title,
                                             List<String> items,DialogInterface.OnClickListener onClickListener){
        return showListDialog(context,title,
                items.toArray(new String[items.size()]),onClickListener);
    }

    /**
     * 列表型dialog
     * @param context
     * @param title
     * @param items
     * @param onClickListener
     * @return
     */
    public static AlertDialog showListDialog(Context context,String title,
                                             String[] items,DialogInterface.OnClickListener onClickListener){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if(!TextUtils.isEmpty(title)){
            builder.setTitle(title);
        }
        AlertDialog dialog = builder.setItems(items,onClickListener).show();
        return  dialog;
    }

    /**
     * 单选框
     * @param context
     * @param title
     * @param items
     * @param defaultItemIndex
     * @param onClickListener
     * @return
     */
    public static AlertDialog showSingleChoiceDialog(Context context,
                                                     String title,String[] items,int defaultItemIndex,
                                                     DialogInterface.OnClickListener onClickListener){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if(!TextUtils.isEmpty(title)){
            builder.setTitle(title);
        }
        AlertDialog dialog = builder.setSingleChoiceItems(items,
                defaultItemIndex,onClickListener)
                .setNegativeButton("取消", null).show();
        return dialog;
    }

    /**
     * 复选框对话框
     * @param context
     * @param title
     * @param items
     * @param defaultCheckedItems
     * @param onMultiChoiceClickListener
     * @param onClickListener
     * @return
     */
    public static AlertDialog showMultiChoiceDialog(Context context,
                                                    String title,String[] items,boolean[] defaultCheckedItems,
                                                    DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener,
                                                    DialogInterface.OnClickListener onClickListener){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if(!TextUtils.isEmpty(title)){
            builder.setTitle(title);
        }
        AlertDialog dialog = builder
                .setMultiChoiceItems(items,defaultCheckedItems,onMultiChoiceClickListener)
                .setPositiveButton("确定", null)
                .setNegativeButton("取消",null)
                .show();
        return dialog;
    }

    /**
     * 选择获取照片的方法，结果在activity的onActivityResult（）方法中
     * @param activity
     */
    public static void showImagePickDialog(final Activity activity){
        String title = "选择获取图片的方式";
        String[] items = new String[]{"拍照","从手机中选择"};
        showListDialog(activity, title, items,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        switch (i){
                            case 0:
                                ImageUtils.openCameraImage(activity);
                                break;
                            case 1:
                                ImageUtils.openLocalImage(activity);
                                break;
                        }
                    }
                });
    }

    /**
     * 显示选择查看软件
     * @param context
     * @param imgUri
     */
    public static void showImahe(Context context,Uri imgUri){
        Intent intent = new Intent(Intent.ACTION_VIEW)；

        intent.setDataAndType(imgUri,"image/*");
        context.startActivity(intent);
    }

    public static class MyBuilder{
        private Context context;

        public MyBuilder(Context context) {
            this.context = context;
        }

        public Context getContext() {
            return context;
        }

        public MyBuilder setTitle(String title){
            return  this;
        }

        public MyBuilder setMessage(String message){
            return  this;
        }

        public MyBuilder setPositiveButton(String text,final DialogInterface.OnClickListener listener){
            return this;
        }

        public MyBuilder setNegativeButton(String text,final DialogInterface.OnClickListener listener){
            return this;
        }

        public Dialog create(){
            final Dialog dialog = new Dialog(context);

            return  dialog;
        }

        public Dialog show(){
            Dialog dialog = create();
            dialog.show();
            return dialog;
        }
    }
}
