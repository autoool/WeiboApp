package com.techidea.weiboapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.techidea.weiboapp.BaseFragment;
import com.techidea.weiboapp.R;
import com.techidea.weiboapp.adapter.StatusAdapter;
import com.techidea.weiboapp.api.BoreWeiboApi;
import com.techidea.weiboapp.api.SimpleRequestListener;
import com.techidea.weiboapp.entity.Status;
import com.techidea.weiboapp.entity.response.StatusTimeLineResponse;
import com.techidea.weiboapp.util.TitleBuilder;
import com.techidea.weiboapp.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2015/7/25.
 */
public class HomeFragment  extends BaseFragment {

    private View view;
    private PullToRefreshListView plv_home;
    private StatusAdapter adapter;
    private View footView;
    private List<Status> statuses = new ArrayList<Status>();
    private int curPage = 1;

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
                .setTitleText("首页");
        plv_home = (PullToRefreshListView)view.findViewById(R.id.lv_home);

        adapter = new StatusAdapter(activity,statuses);
        plv_home.setAdapter(adapter);
        plv_home.setOnRefreshListener(new OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                curPage = 1;
                loadData(curPage);
            }
        });
        plv_home.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                curPage++;
                loadData(curPage);
            }
        });

//        plv_home.set
        footView = View.inflate(activity,R.layout.footview_loading,null);


    }

    private void loadData(final int page){
        BoreWeiboApi api = new BoreWeiboApi(activity);
        api.statusesHome_timeline(page,
                new SimpleRequestListener(activity,null){
                    @Override
                    public void onAllDone() {
                        super.onAllDone();
                        plv_home.onRefreshComplete();
                    }

                    @Override
                    public void onComplete(String response) {
                        super.onComplete(response);

                        if(page==1){
                            statuses.clear();
                        }
                        curPage = page;
                        addData(new Gson().fromJson(response,StatusTimeLineResponse.class));

//                        StatusTimeLineResponse timelineResponse = new Gson().fromJson(response,StatusTimeLineResponse.class);
//                        plv_home.setAdapter(new StatusAdapter(activity, timelineResponse.getStatuses()));
                    }
                });
    }

    private void addData(StatusTimeLineResponse resBean){
        for(Status status : resBean.getStatuses()){
            if(!statuses.contains(status)){
                statuses.add(status);
            }
        }
        adapter.notifyDataSetChanged();
        if(curPage < resBean.getTotal_number()){
            AddFootView(plv_home,footView);
        }else{
            RemoveFootView(plv_home,footView);
        }
    }

    private void AddFootView(PullToRefreshListView plv,View footView){
        ListView lv = plv.getRefreshableView();
        if(lv.getFooterViewsCount() == 1){
            lv.addFooterView(footView);
        }
    }

    private  void RemoveFootView(PullToRefreshListView plv,View footView){
        ListView lv = plv.getRefreshableView();
        if(lv.getFooterViewsCount() > 1){
            lv.removeFooterView(footView);
        }
    }

}
