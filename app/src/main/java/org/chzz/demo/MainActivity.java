package org.chzz.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.chzz.core.net.CHZZClient;
import org.chzz.core.net.callback.Success;
import org.chzz.core.util.MD5Util;
import org.chzz.core.util.ToastUtil;
import org.chzz.demo.model.LoginEntity;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseActivity {
    private Button mHttp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHttp = (Button) findViewById(R.id.but_test);
        mHttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testHttp02();
            }
        });
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

    private void testHttp02() {
        Map<String, Object> data = new HashMap<>();
        data.put("loginName", "hospital");
        data.put("password", MD5Util.getMD5Str32byte("123456"));
        CHZZClient.builder()
                .params(data)
                .onRequest(new RequestStatus(this))
                .url(ConstantValues.LOGIN_URL)
                .success(login)
                .build()
                .post();
    }


    private Success<LoginEntity> login = new Success<LoginEntity>(new LoginEntity()) {
        @Override
        public void onSuccess(LoginEntity entity) {
            ToastUtil.show(entity.getDesc());
        }
    };

}
