package com.techidea.weiboapp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.CheckBox;

/**
 * Created by zhangchao on 2015/9/3.
 */
public class InsideCheckBox extends CheckBox {

    public InsideCheckBox(Context context) {
        super(context);
    }

    public InsideCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InsideCheckBox(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        return super.onTouchEvent(event);
        return false;
    }
}
