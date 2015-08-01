package com.techidea.weiboapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.techidea.weiboapp.BaseFragment;
import com.techidea.weiboapp.R;
import com.techidea.weiboapp.adapter.StatusAdapter;
import com.techidea.weiboapp.api.BoreWeiboApi;
import com.techidea.weiboapp.api.SimpleRequestListener;
import com.techidea.weiboapp.entity.response.StatusTimeLineResponse;
import com.techidea.weiboapp.util.TitleBuilder;
import com.techidea.weiboapp.util.ToastUtils;


/**
 * Created by Administrator on 2015/7/25.
 */
public class HomeFragment  extends BaseFragment {

    private View view;
    private ListView lv_home;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initView();
        loadData(1);
        return view;
//		return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void initView() {
        view = View.inflate(activity,R.layout.frag_home,null);
        new TitleBuilder(view)
                .setTitleText("首页")
                .setLeftText("Left")
                .setLeftOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showToast(activity, "left onclick", Toast.LENGTH_SHORT);
                    }
                });
        lv_home = (ListView)view.findViewById(R.id.lv_home);
    }

    private void loadData(int page){
        BoreWeiboApi api = new BoreWeiboApi(activity);
        api.statusesHome_timeline(page,
                new SimpleRequestListener(activity,null){
                    @Override
                    public void onComplete(String response) {
                        super.onComplete(response);
                        StatusTimeLineResponse timelineResponse = new Gson().fromJson(response,StatusTimeLineResponse.class);
                        lv_home.setAdapter(new StatusAdapter(activity,timelineResponse.getStatuses()));
                    }
                });
    }
}
