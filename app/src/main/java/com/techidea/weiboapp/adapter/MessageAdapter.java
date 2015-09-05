package com.techidea.weiboapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.techidea.weiboapp.R;
import com.techidea.weiboapp.entity.Message;

import java.util.List;

/**
 * Created by zhangchao on 2015/9/5.
 */
public class MessageAdapter extends BaseAdapter {

    private Context context;
    private List<Message> messages;

    public MessageAdapter(Context context, List<Message> messages) {
        this.context = context;
        this.messages = messages;
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Message getItem(int i) {
        return messages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            view = View.inflate(context, R.layout.item_message,null);
            holder.iv_message_item = (ImageView)view.findViewById(R.id.iv_message_item);
            holder.tv_message_item = (TextView)view.findViewById(R.id.tv_message_item);
            view.setTag(holder);
        }else{
            holder = (ViewHolder)view.getTag();
        }

        //set data
        Message message = getItem(i);
        holder.iv_message_item.setImageResource(message.getImg());
        holder.tv_message_item.setText(message.getMessage());

        return view;
    }

    public static class ViewHolder{
        public ImageView iv_message_item;
        public TextView tv_message_item;
    }
}

