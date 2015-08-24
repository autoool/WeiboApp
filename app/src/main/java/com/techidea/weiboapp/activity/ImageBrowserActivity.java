package com.techidea.weiboapp.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.techidea.weiboapp.BaseActivity;
import com.techidea.weiboapp.R;
import com.techidea.weiboapp.entity.PicUrls;
import com.techidea.weiboapp.entity.Status;

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
}
