package org.chzz.demo;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import org.chzz.core.net.CHZZClient;
import org.chzz.core.net.callback.Success;
import org.chzz.core.util.MD5Util;
import org.chzz.core.util.ToastUtil;
import org.chzz.demo.common.ConstantValues;
import org.chzz.demo.model.LoginEntity;
import org.chzz.demo.utils.GsonTools;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.im_animation)
    ImageView imAnimation;
    private Button mHttp;


    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mHttp = (Button) findViewById(R.id.but_test);
        mHttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //testHttp02();
        searchId(null);
    }

    @Override
    protected void setListener() {

    }

    private void test() {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        if (null != adapter) {
            ToastUtil.show("adapter");
            if (adapter.isEnabled()) {
                ToastUtil.show("isEnabled");
            } else {
                ToastUtil.show(adapter.isDiscovering() + "");
            }
        }
    }

    private void testHttp01() {
        Map<String, Object> data = new HashMap<>();
        data.put("loginName", "hospital");
        data.put("password", MD5Util.getMD5Str32byte("123456"));
        CHZZClient.builder()
                .params(data)
                .url(ConstantValues.LOGIN_URL)
                .success(new Success<LoginEntity>(new LoginEntity()) {
                    @Override
                    public void onSuccess(LoginEntity entity) {
                        ToastUtil.show(entity.getDesc());
                    }
                })
                .build()
                .post();
    }

    private void searchId(String text) {
        String url = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.search.catalogSug&query=" + "刘德华";
       String json = CHZZClient.builder()
                .url(url)
                .params(new HashMap<String, Object>())
                .build()
                .asyncGet();
        ToastUtil.show(json);
    }

    private void testHttp02() {
        Map<String, Object> data = new HashMap<>();
        data.put("loginName", "hospital");
        data.put("password", MD5Util.getMD5Str32byte("123456"));
        CHZZClient.builder()
                .params(data)
                .onRequest(new RequestStatus(this))
                .url("http://www.baidu.com/")
                .success(login)
                .build()
                .post();
    }

    private void testHttp03() {
        File file = new File("");
        CHZZClient.builder()
                .file(file)
                .onRequest(new RequestStatus(this))
                .url(ConstantValues.LOGIN_URL)
                .success(login)
                .build()
                .upload();
    }


    private Success<LoginEntity> login = new Success<LoginEntity>(new LoginEntity()) {
        @Override
        public void onSuccess(LoginEntity entity) {
            ToastUtil.show(entity.getDesc());
        }
    };

    @OnClick({R.id.but_service, R.id.but_test})
    public void onClicks(View view) {

        switch (view.getId()) {
            case R.id.but_service:
                startActivity(new Intent(this, BindActivity.class));
                break;
            case R.id.but_test:
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation_alpha);
                imAnimation.startAnimation(animation);

                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
