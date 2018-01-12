package org.chzz.demo;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

import com.chrisplus.rootmanager.RootManager;

import org.chzz.core.install.AutoInstaller;
import org.chzz.core.util.ToastUtil;
import org.chzz.demo.utils.ThreadUtil;


public class MyService extends Service {
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            install();
        }
    };
    public static final String APK_URL = "http://imtt.dd.qq.com/16891/8EDB1BE21114D4BC6ABA4FC484B00A91.apk?fsname=org.chzz.scan_1.2.0_20.apk";

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mHandler.sendEmptyMessageDelayed(1, 60000);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }


    private void install() {
                /* 方案一: 默认安装器 */
        AutoInstaller installer = AutoInstaller.getDefault(this);
//        installer.install(APK_FILE_PATH);
        // installer.setmTempPath(mTempPath);
        installer.setAppName("org.chzz.scan");
        installer.installFromUrl(APK_URL);
        installer.setOnStateChangedListener(new AutoInstaller.OnStateChangedListener() {
            @Override
            public void onStart() {


            }

            @Override
            public void onComplete() {


            }

            @Override
            public void onNeed2OpenService() {

            }
        });

    }
}
