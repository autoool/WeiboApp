package com.techidea.weiboapp.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2015/7/25.
 */
public class ToastUtils {
    private static Toast mToast;

    /*

     */
    public static void showToast(Context context, CharSequence text,
                                 int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(context, text, duration);
        } else {
            mToast.setText(text);
            mToast.setDuration(duration);
        }
        mToast.show();
    }
}
