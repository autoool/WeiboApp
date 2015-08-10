package com.techidea.weiboapp.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.techidea.weiboapp.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/8/9.
 */
public class WriteStatusGridImgsAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Uri> datas;
    private GridView gv;

    @Override
    public int getCount() {
        return datas.size() > 0 ? datas.size() +1 : 0;
    }

    @Override
    public Uri getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            view = View.inflate(context, R.layout.item_grid_image,null);
            holder.iv_image = (ImageView)view.findViewById(R.id.iv_image);
            holder.iv_delete_image = (ImageView)view.findViewById(R.id.iv_delete_image);
            view.setTag(holder);
        }else{
            holder = (ViewHolder)view.getTag();
        }

        int horizontalSpacing = gv.getHorizontalSpacing();
        int width = (gv.getWidth()-horizontalSpacing*2
        -gv.getPaddingLeft() - gv.getPaddingRight())/3;

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width,width);
        holder.iv_image.setLayoutParams(params);

        if(position < getCount() -1){
            //set data
            Uri item = getItem(position);
            holder.iv_image.setImageURI(item);

            holder.iv_delete_image.setVisibility(View.VISIBLE);
            holder.iv_delete_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    datas.remove(position);
                    notifyDataSetChanged();
                }
            });
        }else{
            holder.iv_image.setImageResource(R.drawable.compose_pic_add_more);
            holder.iv_delete_image.setVisibility(View.GONE);
        }
        return view;
    }

    public static class ViewHolder{
        public ImageView iv_image;
        public ImageView iv_delete_image;
    }
}
