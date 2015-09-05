package com.techidea.weiboapp.activity.imgfilter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.techidea.weiboapp.BaseActivity;
import com.techidea.weiboapp.R;
import com.techidea.weiboapp.widget.filter.CropImage;
import com.techidea.weiboapp.widget.filter.CropImageView;

import java.util.logging.LogRecord;

/**
 * Created by Administrator on 2015/8/15.
 * 图片剪裁
 */
public class ImageFilterCropActivity extends BaseActivity {

    private Button mCancel;
    private Button mDetermine;
    private CropImageView mDisplay;
    private ProgressBar mProgressBar;
    private Button mLeft;
    private Button mRight;

    public static final int SHOW_PROGRESS = 0;
    public static final int REMOVE_PROGRESS = 1;

    //	private String mPath;// 修改的图片地址
    private Bitmap mBitmap;// 修改的图片
    private CropImage mCropImage; // 裁剪工具类

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imagefilter_crop_activity);
        findViewById();
        setListener();
        init();
    }

    private void findViewById() {
        mCancel = (Button) findViewById(R.id.imagefilter_crop_cancel);
        mDetermine = (Button) findViewById(R.id.imagefilter_crop_determine);
        mDisplay = (CropImageView) findViewById(R.id.imagefilter_crop_display);
        mProgressBar = (ProgressBar) findViewById(R.id.imagefilter_crop_progressbar);
        mLeft = (Button) findViewById(R.id.imagefilter_crop_left);
        mRight = (Button) findViewById(R.id.imagefilter_crop_right);
    }

    private void setListener(){
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //显示返回对话框
                backDialog();
            }
        });

        mDetermine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //保存修改的图片到本地，并返回图片地址
                mBitmap = mCropImage.cropAndSave();
                Intent intent = new Intent();
                intent.putExtra("bitmap",mBitmap);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        mLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //左旋转
                mCropImage.startRotate(270.f);
            }
        });

        mRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //右旋转
                mCropImage.startRotate(90.f);
            }
        });
    }

    private void init(){
        //接受传递的图片地址
        mBitmap = getIntent().getParcelableExtra("bitmap");
        //获取修改的图片
        //如果图片不存在，则返回，存在则初始化
        if(mBitmap == null){
            Toast.makeText(ImageFilterCropActivity.this,"没有找到图片",Toast.LENGTH_SHORT).show();
            setResult(RESULT_CANCELED);
            finish();
        }else{
            resetImageView(mBitmap);
        }
    }

    private void resetImageView(Bitmap b){
        mDisplay.clear();
        mDisplay.setImageBitmap(b);
        mDisplay.setImageBitmapResetBase(b, true);
        mCropImage = new CropImage(this,mDisplay,handler);
        mCropImage.crop(b);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case SHOW_PROGRESS:
                    mProgressBar.setVisibility(View.VISIBLE);
                    break;
                case REMOVE_PROGRESS:
                    handler.removeMessages(SHOW_PROGRESS);
                    mProgressBar.setVisibility(View.INVISIBLE);
                    break;
            }
        }
    };

    /**
     * 返回对话框
     */
    private void backDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ImageFilterCropActivity.this);
        builder.setTitle("开心网");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setMessage("你确定要取消编辑吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                setResult(RESULT_CANCELED);
                finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.create().show();
    }

    public void onBackPressed(){
        backDialog();
    }

}
