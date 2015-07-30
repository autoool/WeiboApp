package com.techidea.weiboapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.techidea.weiboapp.activity.MainActivity;

/**
 * Created by Administrator on 2015/7/25.
 */
public class BaseFragment extends Fragment {

    protected MainActivity activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        activity = (MainActivity)getActivity();
    }

    protected void intent2Activity(Class<? extends Activity> tarActivity){
        Intent intent = new Intent(activity,tarActivity);
        startActivity(intent);
    }
}
