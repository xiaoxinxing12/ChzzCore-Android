package org.chzz.demo;

import android.app.ProgressDialog;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.chrisplus.rootmanager.RootManager;

import org.chzz.core.install.AutoInstaller;
import org.chzz.core.util.ToastUtil;
import org.chzz.demo.utils.ThreadUtil;

import java.io.File;
import java.io.IOException;

/**
 * Created by copy on 2017/12/30.
 * Description:
 * User: copy
 * Date: 2017-12-30
 * Time: 下午11:00
 */
public class InstallActivity extends BaseActivity {

    @Override
    protected void setListener() {


    }

    public static final String APK_FILE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "Download" + File.separator + "test.apk";
    public static final String CACHE_FILE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "Download";
    public static final String APK_URL = "http://imtt.dd.qq.com/16891/8EDB1BE21114D4BC6ABA4FC484B00A91.apk?fsname=org.chzz.scan_1.2.0_20.apk";
    private ProgressDialog mProgressDialog;
    public static final String mTempPath = Environment.getDataDirectory().getAbsolutePath() + File.separator + "app";
    @Override
    protected void initView() {
        setContentView(R.layout.activity_install);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("正在下载");

        findViewById(R.id.btn_install).setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
         /* 方案一: 默认安装器 */
        AutoInstaller installer = AutoInstaller.getDefault(this);
//        installer.install(APK_FILE_PATH);
       // installer.setmTempPath(mTempPath);
        installer.setAppName("org.chzz.scan");
        installer.installFromUrl(APK_URL);
        ToastUtil.show(mTempPath);
        installer.setOnStateChangedListener(new AutoInstaller.OnStateChangedListener() {
            @Override
            public void onStart() {
                mProgressDialog.show();

            }

            @Override
            public void onComplete() {
                mProgressDialog.dismiss();


                ThreadUtil.runInThread(new Runnable() {
                    @Override
                    public void run() { RootManager.getInstance().installPackage(CACHE_FILE_PATH+"/org.chzz.scan.apk");

                    }
                });
            }

            @Override
            public void onNeed2OpenService() {
                Toast.makeText(InstallActivity.this, "请打开辅助功能服务", Toast.LENGTH_SHORT).show();
            }
        });


//        /* 方案二: 构造器 */
//        AutoInstaller installer = new AutoInstaller.Builder(MainActivity.this)
//                .setMode(AutoInstaller.MODE.AUTO_ONLY)
//                .setCacheDirectory(CACHE_FILE_PATH)
//                .build();
//        installer.install(APK_FILE_PATH);
//        installer.installFromUrl(APK_URL);
//        installer.setOnStateChangedListener(new AutoInstaller.OnStateChangedListener() {
//            @Override
//            public void onStart() {
//                mProgressDialog.show();
//            }
//
//            @Override
//            public void onComplete() {
//                mProgressDialog.dismiss();
//            }
//
//            @Override
//            public void onNeed2OpenService() {
//                Toast.makeText(MainActivity.this, "请打开辅助功能服务", Toast.LENGTH_SHORT).show();
//            }
//        });

    }
}
