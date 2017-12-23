package org.chzz.demo.utils;

import android.os.Build;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.just.library.AgentWebConfig;

/**
 * Created by copy on 2017/9/6.
 */

public class WebViewUtils {
    private static WebSettings mWebSettings;
    public static void setWebView( WebView webView) {

        WebSettings settings = webView.getSettings();
        // 设置是够支持js脚本
        settings.setJavaScriptEnabled(true);

        settings.setLoadsImagesAutomatically(true);
        //  设置字体的大小
        //settings.setTextZoom(120);

        //        第一个方法设置webview推荐使用的窗口，设置为true。
        // 第二个方法是设置webview加载的页面的模式，也设置为true。
        //settings.setSupportZoom(true);


        // 设置可以支持缩放
        //settings.setSupportZoom(true);
        // 设置不出现缩放工具
        //settings.setBuiltInZoomControls(false);
        //设置可在大视野范围内上下左右拖动，并且可以任意比例缩放
        // settings.setUseWideViewPort(true);
        //设置默认加载的可视范围是大视野范围
        //settings.setLoadWithOverviewMode(true);
        //自适应屏幕
      // settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        //settings.setBlockNetworkImage(false);
        //if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
         //   settings.setMixedContentMode(settings.MIXED_CONTENT_ALWAYS_ALLOW);

        //settings.setDefaultTextEncodingName("UTF-8");
        //优先使用缓存:
       // settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        //不使用缓存:
       settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
    }


    /**
     * 设置webview
     *
     * @param webView
     */
    public static void initWebViewSettings(WebView webView) {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDefaultTextEncodingName("UTF-8");
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);

    }


    public static void settings(WebView webView) {


        mWebSettings = webView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setSupportZoom(true);
        mWebSettings.setBuiltInZoomControls(false);
        mWebSettings.setSavePassword(false);
//        if (AgentWebUtils.checkNetwork(webView.getContext())) {
//            //根据cache-control获取数据。
//            mWebSettings.setCacheMode(android.webkit.WebSettings.LOAD_DEFAULT);
//        } else {
//            //没网，则从本地获取，即离线加载
//            mWebSettings.setCacheMode(android.webkit.WebSettings.LOAD_CACHE_ELSE_NETWORK);
//        }
        mWebSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        if (Build.VERSION.SDK_INT >= 21) {
            //适配5.0不允许http和https混合使用情况
            mWebSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else if (Build.VERSION.SDK_INT >= 19) {
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else if (Build.VERSION.SDK_INT < 19) {
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

//        mWebSettings.setRenderPriority(android.webkit.AgentWebSettings.RenderPriority.HIGH);
        mWebSettings.setTextZoom(100);
        mWebSettings.setDatabaseEnabled(true);
        mWebSettings.setAppCacheEnabled(true);
        mWebSettings.setLoadsImagesAutomatically(true);
        mWebSettings.setSupportMultipleWindows(false);
        mWebSettings.setBlockNetworkImage(false);//是否阻塞加载网络图片  协议http or https
        mWebSettings.setAllowFileAccess(true); //允许加载本地文件html  file协议, 这可能会造成不安全 , 建议重写关闭
        mWebSettings.setAllowFileAccessFromFileURLs(false); //通过 file url 加载的 Javascript 读取其他的本地文件 .建议关闭
        mWebSettings.setAllowUniversalAccessFromFileURLs(false);//允许通过 file url 加载的 Javascript 可以访问其他的源，包括其他的文件和 http，https 等其他的源
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        if (Build.VERSION.SDK_INT >=19)
            mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        else
            mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        mWebSettings.setLoadWithOverviewMode(true);
        mWebSettings.setUseWideViewPort(true);
        mWebSettings.setDomStorageEnabled(true);
        mWebSettings.setNeedInitialFocus(true);
        mWebSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        mWebSettings.setDefaultFontSize(16);
        mWebSettings.setMinimumFontSize(12);//设置 WebView 支持的最小字体大小，默认为 8
        mWebSettings.setGeolocationEnabled(true);

        //
        String dir = AgentWebConfig.getCachePath(webView.getContext());

       // LogUtils.i(TAG, "dir:" + dir + "   appcache:" + AgentWebConfig.getCachePath(webView.getContext()));
        //设置数据库路径  api19 已经废弃,这里只针对 webkit 起作用
        mWebSettings.setGeolocationDatabasePath(dir);
        mWebSettings.setDatabasePath(dir);
        mWebSettings.setAppCachePath(dir);

        //缓存文件最大值
       // mWebSettings.setAppCacheMaxSize(Long.MAX_VALUE);


    }
}
