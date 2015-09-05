package com.techidea.weiboapp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * Created by zhangchao on 2015/9/4.
 */
public class WrapHeightListView extends ListView {

    public WrapHeightListView(Context context) {
        super(context);
    }

    public WrapHeightListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapHeightListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int heightSpec;

        if(getLayoutParams().height == LinearLayout.LayoutParams.WRAP_CONTENT){
            heightSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
        }else{
            heightSpec = heightMeasureSpec;
        }
        super.onMeasure(widthMeasureSpec, heightSpec);
    }
}
