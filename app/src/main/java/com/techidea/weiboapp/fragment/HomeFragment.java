package com.techidea.weiboapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.techidea.weiboapp.BaseFragment;
import com.techidea.weiboapp.R;
import com.techidea.weiboapp.util.TitleBuilder;
import com.techidea.weiboapp.util.ToastUtils;


/**
 * Created by Administrator on 2015/7/25.
 */
public class HomeFragment  extends BaseFragment {

    private View view;
    private TextView titlebat_tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        view = View.inflate(activity, R.layout.frag_home, null);

        new TitleBuilder(view)
                .setTitleText("首页")
                .setLeftText("LEFT")
                .setLeftOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        ToastUtils.showToast(activity, "left onclick", Toast.LENGTH_SHORT);
                    }

                });
        return view;
//		return super.onCreateView(inflater, container, savedInstanceState);
    }
}
