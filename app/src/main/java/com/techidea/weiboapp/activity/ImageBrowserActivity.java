package com.techidea.weiboapp.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.techidea.weiboapp.BaseActivity;
import com.techidea.weiboapp.R;
import com.techidea.weiboapp.adapter.ImageBrowserAdapter;
import com.techidea.weiboapp.adapter.ImageBrowserAdapter1;
import com.techidea.weiboapp.entity.BrowserPic;
import com.techidea.weiboapp.entity.PicUrls;
import com.techidea.weiboapp.entity.Status;
import com.techidea.weiboapp.util.ImageUtils;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2015/8/24.
 */
public class ImageBrowserActivity extends BaseActivity implements View.OnClickListener {

    private ViewPager vp_image_browser;
    private TextView tv_image_index;
    private Button btn_save;
    private Button btn_original_image;

    private Status status;
    private ImageBrowserAdapter adapter;
    private ArrayList<PicUrls> imgUrls;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image_browser);
        initData();
        initView();
        setData();
    }

    private void initData(){
        status = (Status) getIntent().getSerializableExtra("status");
        position = getIntent().getIntExtra("position",0);
        //获取图片数据集合（单图也有对应的集合，集合的size为1）
        imgUrls = status.getPic_urls();
//        if(position == -1){
//            imgUrls = new ArrayList<PicUrls>();
//            PicUrls url = new PicUrls();
//            url.setThumbnail_pic(status.getThumbnail_pic());
//            url.setOriginal_pic(status.getOriginal_pic());
//            imgUrls.add(url);
//            position = 0;
//        }else{
//            imgUrls = status.getPic_urls();
//        }
    }

    private void initView(){
        vp_image_browser = (ViewPager)findViewById(R.id.vp_image_browser);
        tv_image_index = (TextView)findViewById(R.id.tv_image_index);
        btn_save = (Button)findViewById(R.id.btn_save);
        btn_original_image = (Button) findViewById(R.id.btn_original_image);

        btn_save.setOnClickListener(this);
        btn_original_image.setOnClickListener(this);
    }

    private void setData(){
        adapter = new ImageBrowserAdapter(this,imgUrls);
        vp_image_browser.setAdapter(adapter);

        final int size = imgUrls.size();
        int initPosition = Integer.MAX_VALUE/2/size*size + position;

        if(size>1){
            tv_image_index.setVisibility(View.VISIBLE);
            tv_image_index.setText((position+1) + "" + size);
        }else{
            tv_image_index.setVisibility(View.GONE);
        }



//        tv_image_index.setVisibility(imgUrls.size() > 1 ? View.VISIBLE : View.GONE);
//
//
//        tv_image_index.setText((position%size + 1)+ "/" + status.getPic_urls().size());
//        vp_image_browser.setCurrentItem(Integer.MAX_VALUE / size / 2 * size + position);
        vp_image_browser.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                int index = i % size;
                tv_image_index.setText((index + 1) + "/" + size);

                PicUrls pic = adapter.getPic(i);
                btn_original_image.setVisibility(pic.isShowOriImag()?
                View.GONE:View.VISIBLE);
//                tv_image_index.setText((i % size + 1)+ "/" + status.getPic_urls().size());
//                BrowserPic pic = adapter.getPic(i%size);
//                btn_original_image.setVisibility(pic.isOriginalPic() ? View.VISIBLE : View.GONE);

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        vp_image_browser.setCurrentItem(initPosition);
    }

    @Override
    public void onClick(View view) {
        PicUrls picUrl = adapter.getPic(vp_image_browser.getCurrentItem());
        switch (view.getId()){
            case R.id.btn_original_image:
                picUrl.setShowOriImag(true);
                adapter.notifyDataSetChanged();
                btn_original_image.setVisibility(View.GONE);
                break;
            case R.id.btn_save:
                Bitmap bitmap = adapter.getBitmap(vp_image_browser.getCurrentItem());

                boolean showOriImag = picUrl.isShowOriImag();
                String fileName = "img-" + (showOriImag?"ori-":"mid-") + picUrl.getImageId();
                String title = fileName.substring(0,fileName.lastIndexOf("."));
                String insertImage = MediaStore.Images.Media.insertImage(
                        getContentResolver(),bitmap,title,"BoreWBImage"
                );
                if(insertImage == null){
                    showToast("图片保存失败");
                }else{
                showToast("图片保存成功");
                }




//                Bitmap bitmap = pic.getBitmap();
//                PicUrls picUrl = pic.getPic();
//                String oriUrl = picUrl.getOriginal_pic();
//                String midUrl = picUrl.getBmiddle_pic();
//                String fileName = "img-" + (pic.isOriginalPic()?
//                    "ori-" + oriUrl.substring(oriUrl.lastIndexOf("/") + 1)
//                : "mid-" + midUrl.substring(midUrl.lastIndexOf("/") + 1));
//
//                if(bitmap != null){
//                    try{
//                        ImageUtils.saveFile(this,bitmap,fileName);
//                        showToast("图片保存成功");
//                    }catch (IOException e){
//                        e.printStackTrace();
//                        showToast("图片保存失败");
//                    }
//                }
                break;
        }
    }
}
