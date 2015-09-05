package com.techidea.weiboapp.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.techidea.weiboapp.R;
import com.techidea.weiboapp.fragment.FragmentController;
import com.techidea.weiboapp.util.ToastUtils;


public class MainActivity extends FragmentActivity implements
        RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private RadioGroup rg_tab;
    private ImageView iv_add;
    private FragmentController controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initView();

        //存放fragment
        controller = FragmentController.getInstance(this, R.id.fl_content);
        controller.showFragment(0);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch(v.getId()){
            case R.id.iv_add:
                Intent intent = new Intent(MainActivity.this,WriteStatusActivity.class);
                startActivityForResult(intent,110);
                break;
            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        // TODO Auto-generated method stub
        switch(checkedId){
            case R.id.rb_home:
                controller.showFragment(0);
                break;
            case R.id.rb_message:
                controller.showFragment(1);
                break;
            case R.id.rb_search:
                controller.showFragment(2);
                break;
            case R.id.rb_user:
                controller.showFragment(3);
                break;
            default:
                break;
        }
    }


    private void initView() {
        rg_tab = (RadioGroup) findViewById(R.id.rg_tab);
        iv_add = (ImageView) findViewById(R.id.iv_add);

        rg_tab.setOnCheckedChangeListener(this);
        iv_add.setOnClickListener(this);
    }

}
