package org.chzz.demo;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import org.chzz.core.app.Chzz;
import org.chzz.core.net.cookie.CookiesManager;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;


/**
 * Created by copy on 2017/8/6.
 * Description:
 * User: copy
 * Date: 2017-08-06
 * Time: 上午10:58
 */
public class App extends Application {
    private static App app;
    public static String Route = "hxsrts";

    @Override
    public void onCreate() {

        super.onCreate();
        app = this;
        //听诊器  如要使用网络听诊器 必须自定义 okHttpClient 传入Chzz
        Stetho.initializeWithDefaults(this);
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .followRedirects(false)
                //超时时间
                .connectTimeout(30, TimeUnit.SECONDS)
                //cookie管理
                .cookieJar(new CookiesManager(this))
                .build();

        //初始化所有配置
        Chzz.init(this)
                .withApiHost(ConstantValues.BASE_URL)
                .withOkHttpClient(client)
                .configure();


    }

    public static App getInstance() {
        return app;
    }
}
