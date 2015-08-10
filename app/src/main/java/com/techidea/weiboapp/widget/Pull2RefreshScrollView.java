package com.techidea.weiboapp.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

/**
 * Created by Administrator on 2015/8/8.
 */
public class Pull2RefreshScrollView extends PullToRefreshScrollView {

    public Pull2RefreshScrollView(Context context) {
        super(context);
        init(context);
    }

    public Pull2RefreshScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Pull2RefreshScrollView(Context context, Mode mode) {
        super(context, mode);
        init(context);
    }

    public Pull2RefreshScrollView(Context context, Mode mode, AnimationStyle style) {
        super(context, mode, style);
        init(context);
    }

    private void init(Context context){

    }

    private OnScrollChangeListener onScrollChangeListener;

    private void setOnScrollChangeListener(OnScrollChangeListener onScrollChangeListener){
        this.onScrollChangeListener = onScrollChangeListener;

    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        if(onScrollChangeListener != null){
            onScrollChangeListener.onScrollChanged(l,t,oldl,oldt);
        }
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public static interface OnScrollChangeListener{
        void onScrollChanged(int l,int t,int oldl,int oldt);
    }
}
