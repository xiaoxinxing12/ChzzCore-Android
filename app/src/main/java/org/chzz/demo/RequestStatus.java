package org.chzz.demo;

import android.content.Context;

import org.chzz.core.net.callback.IRequest;
import org.chzz.widget.CHZZAlertDialog;

/**
 * Created by copy on 2017/9/12.
 */

public class RequestStatus implements IRequest {

    private CHZZAlertDialog mLoadingDialog;
    private Context context;

    public RequestStatus(Context context) {
        this.context = context;
    }

    @Override
    public void onRequestStart() {
        showLoadingDialog();
    }

    @Override
    public void onRequestEnd(int code ,String msg) {
        dismissLoadingDialog();
    }

    /**
     * 加载数据时show
     */
    public void showLoadingDialog() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new CHZZAlertDialog(context, 5);
            mLoadingDialog.getProgressHelper().setBarColor(context.getResources().getColor(R.color.colorPrimary));
            //mLoadingDialog.setCustomImage(R.drawable.ic_launcher);
            mLoadingDialog.setCancelable(false);
            mLoadingDialog.setTitleText("数据加载中...");
        }
        if (mLoadingDialog != null)
            mLoadingDialog.show();
    }

    /**
     * 加载完数据dismiss
     */
    public void dismissLoadingDialog() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }
}
