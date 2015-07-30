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

}
