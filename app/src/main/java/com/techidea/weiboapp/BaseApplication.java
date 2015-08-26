package com.techidea.weiboapp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.techidea.weiboapp.entity.User;
import com.techidea.weiboapp.util.ImageOptHelper;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2015/7/25.
 */
public class BaseApplication  extends Application {

    private List<Activity> activityList = new LinkedList<Activity>();

    private static BaseApplication instance;
// 单例模式中获取唯一的ExitApplication 实例
    public static BaseApplication getInstance(){
        if(null == instance){
            instance = new BaseApplication();
        }
        return  instance;
    }

    public User currentUser;


    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        initImageLoader(this);
    }

    // 初始化图片操作
    private void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you
        // may tune some of them,
        // or you can create default configuration by
        // ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(context)
                .threadPriority(Thread.NORM_PRIORITY-2)
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .defaultDisplayImageOptions(ImageOptHelper.getImageOptions())
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);

    }

    // 添加Activity 到容器中
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    // 从容器中删除Activity
    public void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    // 遍历所有Activity 并finish
    public void exit() {
        for (Activity activity : activityList) {
            activity.finish();
        }
        System.exit(0);
    }
}
