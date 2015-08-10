package com.techidea.weiboapp.adapter;

import android.content.Context;
import android.text.SpannableString;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.techidea.weiboapp.R;
import com.techidea.weiboapp.entity.Comment;
import com.techidea.weiboapp.entity.User;
import com.techidea.weiboapp.util.DateUtils;
import com.techidea.weiboapp.util.StringUtils;
import com.techidea.weiboapp.util.ToastUtils;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Administrator on 2015/8/7.
 */
public class StatusCommentAdapter extends BaseAdapter{

    private Context context;
    private List<Comment> comments;
    private ImageLoader imageLoader;

    public StatusCommentAdapter(Context context, List<Comment> comments) {
        this.context = context;
        this.comments = comments;
        this.imageLoader = ImageLoader.getInstance();
    }

    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Comment getItem(int i) {
        return comments.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      ViewHolderList holder;
        if(convertView == null){
            holder = new ViewHolderList();
            convertView = View.inflate(context, R.layout.item_comment,null);
            holder.ll_comments = (LinearLayout)convertView
                    .findViewById(R.id.ll_comments);
            holder.iv_avatar = (ImageView)convertView
                    .findViewById(R.id.iv_avatar);
            holder.tv_caption = (TextView)convertView
                    .findViewById(R.id.tv_caption);
            holder.tv_comment = (TextView)convertView
                    .findViewById(R.id.tv_comment);
            holder.tv_subhead = (TextView)convertView
                    .findViewById(R.id.tv_subhead);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolderList)convertView.getTag();
        }

        Comment comment = getItem(position);
        User user = comment.getUser();
        imageLoader.displayImage(user.getProfile_image_url(), holder.iv_avatar);
        holder.tv_subhead.setText(user.getName());
        holder.tv_caption.setText(DateUtils.getShortTime(comment.getCreated_at()));
        SpannableString weiboContent = StringUtils.getWeiboContent(
                context,holder.tv_comment,comment.getText()
        );
        holder.tv_comment.setText(weiboContent);
        holder.ll_comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showToast(context,"回复评论", Toast.LENGTH_SHORT);
            }
        });
        return  convertView;
    }

    public static class ViewHolderList{
        public LinearLayout ll_comments;
        public ImageView iv_avatar;
        public TextView tv_subhead;
        public TextView tv_caption;
        public TextView tv_comment;
    }
}
