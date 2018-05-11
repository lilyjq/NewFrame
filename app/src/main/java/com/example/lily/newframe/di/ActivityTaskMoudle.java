package com.example.lily.newframe.di;

import android.app.Application;
import android.content.Context;

import com.example.lily.newframe.common.Config;
import com.example.lily.newframe.common.HttpManager;
import com.example.lily.newframe.util.NetworkUtils;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;


import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ljq
 * on 2018/5/8.
 */

@Module
public  class ActivityTaskMoudle {

    private Context context;
    public ActivityTaskMoudle(Application context){
        this.context = context;

    }


    @Provides
    @Singleton
    Retrofit provideRetrofit(@Named("baseurl") String baseUrl, OkHttpClient client){
          new Retrofit.Builder()
                  .baseUrl(baseUrl)
                  .addConverterFactory(GsonConverterFactory.create())
                  .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                  .build();

        return null;

    }

    @Provides
    @Named("baseurl")
    String privodeBaseUrl(){
        return Config.URL.BASE_URL;
    }


    @Provides
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor, Cache cache,
                                     @Named("cache_interceptor") Interceptor CacheInterceptor,
                                     @Named("param_interceptor") Interceptor paramInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(CacheInterceptor)
                .addNetworkInterceptor(new StethoInterceptor())
                .addInterceptor(CacheInterceptor)
                .addInterceptor(paramInterceptor)
                .cache(cache)
                .build();

    }

    @Provides
    @Singleton
    HttpManager provideHttpManager(){
        return  new HttpManager();
    }


    //50M 的缓存
    @Provides
    Cache provideCache() {
        File file = new File(context.getExternalCacheDir(), "cache_network");
        return new Cache(file, 50 * 1024 * 1024);
    }


    @Provides
    @Named("cache_interceptor")
    Interceptor provideCacheInterceptor() {

        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetworkUtils.isConnected(context)) {
                    request = request.newBuilder().
                            cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (NetworkUtils.isConnected(context)) {
                    CacheControl cacheControl = request.cacheControl();
                    String value = cacheControl.toString();
                    String noCache = "public,max-age=" + 0;
                    response = response.newBuilder()
                            .addHeader("Cache-Control", (value == null || value.trim().length() == 0) ? noCache : value)
                            .build();

                } else {
                    response = response.newBuilder()
                            .addHeader("Cache-Control", "public,only-if-cached,max-stale=" + 24 * 60 * 60 * 7)
                            .build();
                }
                return response;
            }
        };

    }
/**
    *
     * @return 日志打印的interceptor
   */
    @Provides
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    @Named("param_interceptor")
    Interceptor provideParamInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request;
                Request origin_request = chain.request();
                String method = origin_request.method();
                if (method != null && method.equals("GET")) {
                    HttpUrl.Builder builder = origin_request.url().newBuilder();
                    HttpUrl httpUrl = builder.build();
                    int size = httpUrl.querySize();
                    for (int i = 0; i < size; i++) {//遍历所有的参数，如果你希望加密的话
                        String name = httpUrl.queryParameterName(i);
                        String value = httpUrl.queryParameterValue(i);
                        builder.setQueryParameter(name, value);//在这里可以将value加密
                    }
                    httpUrl = builder.addQueryParameter("username", "username")
                            .build();
                    request = origin_request.newBuilder().url(httpUrl).build();
                    return chain.proceed(request);
                } else if (method != null && method.equals("POST")) {
                    RequestBody body = origin_request.body();
                    if (body != null && body instanceof FormBody) {
                        FormBody.Builder builder = new FormBody.Builder();
                        FormBody formBody = (FormBody) body;
                        int size = formBody.size();
                        for (int i = 0; i < size; i++) {
                            String name = formBody.name(i);
                            String value = formBody.value(i);
                            builder.add(name, value);
                        }
                        formBody = builder.add("username", "username").build();
                        request = origin_request.newBuilder().post(formBody).build();
                        return chain.proceed(request);
                    }

                }

                return chain.proceed(origin_request);
            }
        };
    }





}
