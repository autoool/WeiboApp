package com.techidea.weiboapp.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.techidea.weiboapp.R;
import com.techidea.weiboapp.entity.Emotion;

import java.util.List;

/**
 * Created by Administrator on 2015/8/9.
 */
public class EmotionGvAdapter extends BaseAdapter{

    private Context context;
    private List<String> emotionNames;
    private int itemWidth;

    public EmotionGvAdapter(Context context, List<String> emotionNames, int itemWidth) {
        this.context = context;
        this.emotionNames = emotionNames;
        this.itemWidth = itemWidth;
    }

    @Override
    public int getCount() {
        return emotionNames.size() + 1;
    }

    @Override
    public String getItem(int i) {
        return emotionNames.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView iv = new ImageView(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(itemWidth,itemWidth);
        iv.setPadding(itemWidth/8,itemWidth/8,itemWidth/8,itemWidth/8);
        iv.setLayoutParams(params);

        if(i == getCount()-1){
            iv.setImageResource(R.drawable.emotion_delete_icon);
        }else{
            String emotionName = emotionNames.get(i);
            iv.setImageResource(Emotion.getImgByName(emotionName));
        }
        return null;
    }
}
