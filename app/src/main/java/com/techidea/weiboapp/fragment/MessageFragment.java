package com.techidea.weiboapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.techidea.weiboapp.BaseFragment;
import com.techidea.weiboapp.R;
import com.techidea.weiboapp.util.TitleBuilder;
import com.techidea.weiboapp.util.ToastUtils;

/**
 * Created by Administrator on 2015/7/25.
 */
public class MessageFragment extends BaseFragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = View.inflate(activity, R.layout.frag_message, null);

        new TitleBuilder(view)
                .setTitleText("Message")
                .setRightImage(R.mipmap.ic_launcher)
                .setRightOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showToast(activity, "right click~", Toast.LENGTH_SHORT);
                    }
                });

        return view;
    }
}
