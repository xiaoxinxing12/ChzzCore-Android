package org.chzz.demo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by copy on 2017/12/9.
 * Description:
 * User: copy
 * Date: 2017-12-09
 * Time: 下午12:06
 */
public class BindService extends Service {

    LocalBinder binder = new LocalBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("log",android.os.Process.myPid()+"");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }


    public class LocalBinder extends Binder {
        public BindService getService() {
            Log.i("log",android.os.Process.myPid()+"");
            return BindService.this;
        }
    }


    public void getData() {
        Log.i("bindService", "11111");
    }
}
