package com.techidea.weiboapp.entity;

import android.graphics.Bitmap;

/**
 * Created by zhangchao on 2015/8/25.
 */
public class BrowserPic {

    private PicUrls pic;
    private boolean isOriginalPic;
    private Bitmap bitmap;

    public PicUrls getPic() {
        return pic;
    }

    public void setPic(PicUrls pic) {
        this.pic = pic;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public boolean isOriginalPic() {
        return isOriginalPic;
    }

    public void setIsOriginalPic(boolean isOriginalPic) {
        this.isOriginalPic = isOriginalPic;
    }
}
