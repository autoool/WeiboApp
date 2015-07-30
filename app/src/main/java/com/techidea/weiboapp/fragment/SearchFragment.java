package com.techidea.weiboapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techidea.weiboapp.BaseFragment;
import com.techidea.weiboapp.R;


/**
 * Created by Administrator on 2015/7/25.
 */
public class SearchFragment  extends BaseFragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = View.inflate(activity, R.layout.frag_search, null);
        return view;
    }
}
