package com.techidea.weiboapp.api;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboParameters;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.legacy.WeiboAPI;
import com.techidea.weiboapp.constants.AccessTokenKeeper;
import com.techidea.weiboapp.constants.URLs;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


/**
 * Created by Administrator on 2015/7/30.
 */
public class BoreWeiboApi extends WeiboAPI {

    private Handler mainLooperHandler = new Handler(Looper.getMainLooper());


    public BoreWeiboApi(Context context) {
        this(AccessTokenKeeper.readAccessToken(context));
    }

    public BoreWeiboApi(Oauth2AccessToken oauth2AccessToken) {
        super(oauth2AccessToken);
    }

    public void requestInMainLooper(String url,WeiboParameters params,
                                    String httpMethod,final RequestListener listener){
        request(url, params, httpMethod, new RequestListener() {
            @Override
            public void onComplete(final String response) {
                mainLooperHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onComplete(response);
                    }
                });
            }

            @Override
            public void onComplete4binary(final ByteArrayOutputStream responseOS) {
                mainLooperHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onComplete4binary(responseOS);
                    }
                });
            }

            @Override
            public void onIOException(final IOException e) {
                mainLooperHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onIOException(e);
                    }
                });
            }

            @Override
            public void onError(final WeiboException e) {
                mainLooperHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onError(e);
                    }
                });
            }
        });
    }

    @Override
    protected void request(String url, WeiboParameters params, String httpMethod, RequestListener listener) {
        super.request(url, params, httpMethod, listener);
    }

    /**
     * 获取当前登录用户及其所关注用户的最新微博
     *
     * @param page
     *            返回结果的页码。(单页返回的记录条数，默认为20。)
     * @param listener
     */
    public void statusesHome_timeline(long page,RequestListener listener){
        WeiboParameters parameters = new WeiboParameters();
        parameters.add("page",page);
        requestInMainLooper(URLs.statusesHome_timeline, parameters, HTTPMETHOD_GET, listener);
    }

    /**
     * 根据微博id 返回某条微博的评论数
     * @param id 需要查询的微博id
     * @param page 返回结果的页码 (单页返回的记录条数默认50)
     * @param listener
     */
    public void commentShow(long id,long page,RequestListener listener){
        WeiboParameters params = new WeiboParameters();
        params.add("id",id);
        params.add("page",page);
        requestInMainLooper(URLs.commentsShow,params,WeiboAPI.HTTPMETHOD_GET,listener);
    }

    public void commentsCreate(long id,String comment,RequestListener listener){
        WeiboParameters params = new WeiboParameters();
        params.add("id",id);
        params.add("comment",comment);
        requestInMainLooper(URLs.commentsCreate,params,WeiboAPI.HTTPMETHOD_POST,listener);
    }

}
