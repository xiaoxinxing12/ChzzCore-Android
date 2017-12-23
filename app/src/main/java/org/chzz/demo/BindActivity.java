package org.chzz.demo;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.chzz.demo.service.BindService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by copy on 2017/12/9.
 * Description:
 * User: copy
 * Date: 2017-12-09
 * Time: 上午11:52
 */
public class BindActivity extends BaseActivity {
    @BindView(R.id.but_service)
    Button butService;
    BindService.LocalBinder binder;

    @Override
    protected void initView() {
        Log.i("log",android.os.Process.myPid()+"");
        setContentView(R.layout.activity_bindservice);
        ButterKnife.bind(this);
    }

    @Override
    protected void setListener() {

    }

    @OnClick({R.id.but_service})
    public void onClicks(View view) {
        switch (view.getId()) {
            case R.id.but_service:
                Intent intent = new Intent(this, BindService.class);
                bindService(intent, connection, Service.BIND_AUTO_CREATE);
                break;
        }
    }


    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            binder = (BindService.LocalBinder) iBinder;
            binder.getService().getData();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
