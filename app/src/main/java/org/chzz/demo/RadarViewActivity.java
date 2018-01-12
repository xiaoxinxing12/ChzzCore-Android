package org.chzz.demo;

import android.os.Message;

import com.king.view.radarview.RadarView;

import java.util.Random;
import java.util.logging.Handler;

/**
 * Created by copy on 2018/1/4.
 */

public class RadarViewActivity extends BaseActivity {
    RadarView mRadarView;
    DashboardView2 mDashboardView2;
    DashboardView3 mDashboardView3;

    private android.os.Handler handler = new android.os.Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mDashboardView3.setCreditValue(new Random().nextInt(950 - 50) + 50);
            handler.sendEmptyMessageDelayed(1,500);
        }
    };
    @Override
    protected void initView() {
        setContentView(R.layout.activity_radarview);
         mRadarView= (RadarView) findViewById(R.id.rv);
        mDashboardView2= (DashboardView2) findViewById(R.id.dashboard_view_2);
        mDashboardView3= (DashboardView3) findViewById(R.id.dashboard_view_3);
    }

    @Override
    protected void setListener() {
        mRadarView.start();
        mDashboardView2.setCreditValueWithAnim(new Random().nextInt(950 - 50) + 50);
        mDashboardView3.setCreditValue(new Random().nextInt(950 - 350) + 350);
        handler.sendEmptyMessageDelayed(1,500);

    }
}
