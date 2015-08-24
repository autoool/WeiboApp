package com.techidea.weiboapp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

import com.techidea.weiboapp.R;

/**
 * Created by Administrator on 2015/8/23.
 */
public class UnderlineIndicatorView extends LinearLayout {

    private int mCurrentPosition;

    public UnderlineIndicatorView(Context context) {
        this(context,null);
    }

    public UnderlineIndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(HORIZONTAL);
        int count = 4;
        for (int i = 0; i < count; i++) {
            View view = new View(context);
            LayoutParams params = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
            params.weight = 1;
            view.setLayoutParams(params);
            view.setBackgroundResource(R.color.transparent);
            addView(view);
        }
    }

    public void setCurrentItemWithoutAnim(int item){
        final View oldChild = getChildAt(mCurrentPosition);
        final View newChild = getChildAt(item);
        oldChild.setBackgroundResource(R.color.transparent);
        newChild.setBackgroundResource(R.color.orange);

        mCurrentPosition = item;
        invalidate();
    }

    public void setCurrentItem(int item){
        final View oldChild = getChildAt(mCurrentPosition);
        final View newChild = getChildAt(item);

        TranslateAnimation translationAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF,0,
                Animation.RELATIVE_TO_SELF,item-mCurrentPosition,
                Animation.RELATIVE_TO_SELF,0,
                Animation.RELATIVE_TO_SELF,0
        );

        translationAnimation.setDuration(200);
        translationAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                oldChild.setBackgroundResource(R.color.transparent);
                newChild.setBackgroundResource(R.color.orange);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        oldChild.setAnimation(translationAnimation);
        mCurrentPosition = item;
        invalidate();

    }
}
