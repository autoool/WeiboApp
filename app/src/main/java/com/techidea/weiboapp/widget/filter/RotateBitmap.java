package com.techidea.weiboapp.widget.filter;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * Created by Administrator on 2015/8/12.
 */
public class RotateBitmap {

    public static final String TAG = "RatateBitmap";
    private Bitmap mBitmap;
    private int mRotation;

    public RotateBitmap(Bitmap mBitmap) {
        this.mBitmap = mBitmap;
        mRotation = 0;
    }

    public RotateBitmap(Bitmap mBitmap, int mRotation) {
        this.mBitmap = mBitmap;
        this.mRotation = mRotation % 360;
    }

    public Bitmap getmBitmap() {
        return mBitmap;
    }

    public void setmBitmap(Bitmap mBitmap) {
        this.mBitmap = mBitmap;
    }

    public int getmRotation() {
        return mRotation;
    }

    public void setmRotation(int mRotation) {
        this.mRotation = mRotation;
    }

    public Matrix getRotateMatrix(){
        //By default this is an indentity matrix
        Matrix matrix = new Matrix();
        if(mRotation != 0){
            int cx = mBitmap.getWidth()/2;
            int cy = mBitmap.getHeight()/2;

            matrix.preTranslate(-cx,-cy);
            matrix.postRotate(mRotation);
            matrix.postTranslate(getWidth()/2,getHeight()/2);
        }
        return matrix;
    }

    public boolean isOrientationChanged(){
        return (mRotation/90)%2 !=0;
    }

    public int getHeight(){
        if(isOrientationChanged()){
            return mBitmap.getWidth();
        }else{
            return mBitmap.getHeight();
        }
    }

    public int getWidth(){
        if(isOrientationChanged()){
            return mBitmap.getHeight();
        }else{
            return mBitmap.getWidth();
        }
    }

    public void recycle(){
        if(mBitmap != null){
            mBitmap.recycle();
            mBitmap = null;
        }
    }
}
