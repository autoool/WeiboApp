package com.techidea.weiboapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.techidea.weiboapp.R;
import com.techidea.weiboapp.entity.UserItem;
import com.techidea.weiboapp.util.ToastUtils;

import java.util.List;

/**
 * Created by zhangchao on 2015/9/4.
 */
public class UserItemAdapter extends BaseAdapter {

    private Context context;
    private List<UserItem> datas;

    public UserItemAdapter(Context context, List<UserItem> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas==null ? 0 : datas.size();
    }

    @Override
    public UserItem getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            view = View.inflate(context, R.layout.item_user, null);
            holder.v_divider = view.findViewById(R.id.v_divider);
            holder.ll_content = view.findViewById(R.id.ll_content);
            holder.iv_left = (ImageView)view.findViewById(R.id.iv_left);
            holder.tv_subhead = (TextView)view.findViewById(R.id.tv_subhead);
            holder.tv_caption = (TextView)view.findViewById(R.id.tv_caption);
            view.setTag(holder);
        }else{
            holder = (ViewHolder)view.getTag();
        }

        UserItem item = getItem(i);
        holder.v_divider.setVisibility(item.isShowTopDivider() ? View.VISIBLE : View.GONE);
        holder.iv_left.setImageResource(item.getLeftImg());
        holder.tv_subhead.setText(item.getSubhead());
        holder.tv_caption.setText(item.getCaption());

        holder.ll_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showToast(context, "点击了Item" + i, Toast.LENGTH_SHORT);
            }
        });

        return view;
    }

    public static class ViewHolder{
        public View v_divider;
        public View ll_content;
        public ImageView iv_left;
        public TextView tv_subhead;
        public TextView tv_caption;
    }
}
