package com.techidea.weiboapp.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.techidea.weiboapp.entity.BrowserPic;
import com.techidea.weiboapp.entity.PicUrls;
import com.techidea.weiboapp.util.DisplayUtils;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by zhangchao on 2015/8/25.
 */
public class ImageBrowserAdapter extends PagerAdapter{

    private Activity context;
    private ArrayList<BrowserPic> pics;
    private ImageLoader mImageLoader;

    public ImageBrowserAdapter(Activity context, ArrayList<PicUrls> pics) {
        this.context = context;
        this.mImageLoader = ImageLoader.getInstance();
        initImgs(pics);
    }

    private void initImgs(ArrayList<PicUrls> picUrls){
        pics = new ArrayList<BrowserPic>();
        BrowserPic browserPic;
        for (PicUrls picUrl : picUrls){
            browserPic = new BrowserPic();
            browserPic.setPic(picUrl);
            Bitmap oBm = mImageLoader.getMemoryCache().get(picUrl.getOriginal_pic());
            File disCache = mImageLoader.getDiskCache().get(picUrl.getOriginal_pic());
            if(oBm != null || disCache != null){
                browserPic.setIsOriginalPic(true);
                oBm.recycle();
            }
            pics.add(browserPic);
        }
    }

    public BrowserPic getPic(int position){
        return pics.get(position);
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        ScrollView sv = new ScrollView(context);
        FrameLayout.LayoutParams svParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
        );
        sv.setLayoutParams(svParams);

        LinearLayout ll = new LinearLayout(context);
        LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        ll.setLayoutParams(llParams);
        sv.addView(ll);

        final int screenHeight = DisplayUtils.getScreenHeightPixels(context);
        final int screenWidth = DisplayUtils.getScreenWidthPixels(context);

        final ImageView iv = new ImageView(context);

        final BrowserPic browserPic = pics.get(position % pics.size());
        PicUrls picUrls = browserPic.getPic();

        String url = browserPic.isOriginalPic() ? picUrls.getOriginal_pic() : picUrls.getBmiddle_pic();

        mImageLoader.loadImage(url, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {

            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                browserPic.setBitmap(bitmap);

                float scale = (float)bitmap.getHeight()/bitmap.getWidth();
                int height = Math.max((int) (screenWidth * scale),screenHeight);
                LinearLayout.LayoutParams params = new LinearLayout
                        .LayoutParams(screenWidth,height);
                iv.setLayoutParams(params);
                iv.setImageBitmap(bitmap);
            }

            @Override
            public void onLoadingCancelled(String s, View view) {

            }
        });
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.finish();
            }
        });
        container.addView(sv, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        return sv;
    }

    @Override
    public int getCount() {
        if(pics.size() > 1){
            return  Integer.MAX_VALUE;
        }
        return pics.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View)object);
    }
}
