package com.techidea.weiboapp.activity.imgfilter;

import android.os.Bundle;
import android.view.View;

import com.techidea.weiboapp.BaseActivity;
import com.techidea.weiboapp.R;

/**
 * Created by Administrator on 2015/8/12.
 * 图片编辑类
 */
public class ImageFilterActivity extends BaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagefilter);
    }
}
