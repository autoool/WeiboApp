package com.techidea.weiboapp.api;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

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
        requestInMainLooper(URLs.commentsShow, params, WeiboAPI.HTTPMETHOD_GET, listener);
    }

    /**
     * 对一条微博进行评论
     * @param id
     * @param comment
     * @param listener
     */
    public void commentsCreate(long id,String comment,RequestListener listener){
        WeiboParameters params = new WeiboParameters();
        params.add("id",id);
        params.add("comment",comment);
        requestInMainLooper(URLs.commentsCreate,params,WeiboAPI.HTTPMETHOD_POST,listener);
    }


    /**
     * 转换get方式的url,将get参数与url拼接
     * @param url
     * @param getParams
     * @return
     */
    public static String parseGetUrlWithParams(String url,WeiboParameters getParams){
        StringBuilder newUrl = new StringBuilder(url);
        if(getParams != null && getParams.size() > 0){
            newUrl.append("?");
            for (int i = 0; i <getParams.size() ; i++) {
                newUrl.append(getParams.getKey(i) + "=" + getParams.getValue(i) + "&");
            }
            newUrl.substring(0,newUrl.length()-2);
        }
        return newUrl.toString();
    }

    /**
     * 根据用户ID获取用户信息（uid和screen_name二选一）
     * @param uid 根据用户ID获取用户信息
     * @param screen_name   需要查询的用户昵称
     * @param listener
     */
    public void userShow(String uid,String screen_name,RequestListener listener){
        WeiboParameters params = new WeiboParameters();
        if(!TextUtils.isEmpty(uid)){
            params.add("uid",uid);
        }else if(!TextUtils.isEmpty(screen_name)){
            params.add("screen_name",screen_name);
        }
        requestInMainLooper(URLs.usersShow,params, WeiboAPI.HTTPMETHOD_GET,listener);
    }

    /**
     * 上传图片并发布一条新微博
     * @param status 要发布微博的文本内容
     * @param imgFilePath 上传图片的绝对路径
     * @param listener
     */
    public void statusesUpload(String status,String imgFilePath,RequestListener listener){
        WeiboParameters params = new WeiboParameters();
        params.add("status",status);
        params.add("pic",imgFilePath);
        requestInMainLooper(URLs.statusesUpload,params,WeiboAPI.HTTPMETHOD_POST,listener);
    }


    /**
     * 获取某个用户最新发表的微博列表(uid and screen_name choice one)
     * @param uid
     * @param screen_name
     * @param page
     * @param listener
     */
    public void statusesUser_timeline(long uid,String screen_name,long page,RequestListener listener){
        WeiboParameters params = new WeiboParameters();
        if(uid > 0){
            params.add("uid",uid);
        }else if(!TextUtils.isEmpty(screen_name)){
            params.add("screen_name",screen_name);
        }
        params.add("page",page);
        requestInMainLooper(URLs.statusesUser_timeline,params,WeiboAPI.HTTPMETHOD_GET,listener);
    }

    /**
     * 转发微博
     * @param id 转发微博的ID
     * @param status 添加的转发文本, 必须做URLencode，内容不超过140个汉字，不填则默认为“转发微博”
     * @param is_comment  是否在转发的同时发表评论，0：否、1：评论给当前微博、2：评论给原微博、3：都评论，默认为0
     * @param listener
     */
    public void statusesRepost(long id,String status,int is_comment,RequestListener listener){
        WeiboParameters params = new WeiboParameters();
        params.add("id",id);
        params.add("status",status);
        params.add("is_comment",is_comment);
        requestInMainLooper(URLs.statusesRepost,params,WeiboAPI.HTTPMETHOD_POST,listener);
    }


    /**
     * 发布或者转发一条微博
     * @param status
     * @param imgFilePath
     * @param retweetedStatsId
     * @param listener
     */
    public void statusesSend(String status,String imgFilePath,long retweetedStatsId,
                             RequestListener listener){
        String url;
        WeiboParameters params = new WeiboParameters();
        params.add("status",status);
        if(retweetedStatsId > 0){
            //如果是转发微博，设置被转发者的id
            params.add("id",retweetedStatsId);
            url = URLs.statusesRepost;
        }else if(!TextUtils.isEmpty(imgFilePath)){
            //如果有图片，则调用upload接口设置图片路径
            params.add("pic",imgFilePath);
            url = URLs.statusesUpload;
        }else{
            //如果无图片,则调用update接口
            url = URLs.statusesUpdate;
        }
        requestInMainLooper(url,params,WeiboAPI.HTTPMETHOD_POST,listener);
    }

    /**
     * 获取指定微博的转发微博列表
     * @param id
     * @param page
     * @param listener
     */
    public void statusesRepostTimeline(long id,int page,RequestListener listener){
        WeiboParameters params = new WeiboParameters();
        params.add("id",id);
        params.add("page",page);
        requestInMainLooper(URLs.statusesRepost_timeline,params,WeiboAPI.HTTPMETHOD_GET,listener);
    }

    /**
     * 根据用户ID获取用户信息(uid和screen_name二选一)
     *
     * @param uid
     *            根据用户ID获取用户信息
     * @param screen_name
     *            需要查询的用户昵称。
     * @param listener
     */
    public void usersShow(String uid,String screen_name,RequestListener listener){
        WeiboParameters params = new WeiboParameters();
        if(!TextUtils.isEmpty(uid)){
            params.add("uid",uid);
        }else if(!TextUtils.isEmpty(screen_name)){
            params.add("screen_name",screen_name);
        }
        requestInMainLooper(URLs.usersShow,params,WeiboAPI.HTTPMETHOD_GET,listener);
    }



}
