package org.chzz.demo;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.chzz.adapter.CHZZDivider;
import org.chzz.core.util.ToastUtil;
import org.chzz.demo.common.CommonRecyclerAdapter;
import org.chzz.demo.common.ConstantValues;
import org.chzz.demo.utils.ThreadUtil;
import org.chzz.refresh.CHZZMoocStyleRefreshViewHolder;
import org.chzz.refresh.CHZZRefreshLayout;
import org.chzz.widget.CHZZLoadDataLayout;

/**
 * Created by copy on 2017/9/12.
 */

public abstract class BaseActivity extends AppCompatActivity implements CHZZRefreshLayout.CHZZRefreshLayoutDelegate, View.OnClickListener {
    protected App mApp;
    protected CHZZMoocStyleRefreshViewHolder leftRefreshViewHolder;
    protected CHZZRefreshLayout mRefreshLayout;
    public CHZZLoadDataLayout mLoadLayout;
    protected LinearLayoutManager layoutManager;
    protected TextView mTitle;
    protected RecyclerView mDataRv;
    protected CommonRecyclerAdapter mAdapter;
    protected int pageIndex = 1;
    protected int pageSize = 10;
    protected int pageCount = 0;
    protected Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApp = App.getInstance();

        initView();
        try {
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
        } catch (Exception e) {
            mToolbar = null;
        }
        try {
            mLoadLayout = (CHZZLoadDataLayout) findViewById(R.id.loadLayout);
            mRefreshLayout = (CHZZRefreshLayout) findViewById(R.id.refreshLayout);
            mDataRv = (RecyclerView) findViewById(R.id.rvData);
            mTitle = (TextView) findViewById(R.id.tv_title);
        } catch (Exception e) {

        }
        if (null != mToolbar) {
            //设置可见
            setSupportActionBar(mToolbar);
            //显示返回键
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        setListener();
        onUserVisible();


    }


    protected abstract void initView();

    /**
     * 当fragment对用户可见时，会调用该方法，可在该方法中懒加载网络数据
     */
    protected void onUserVisible() {
    }

    protected abstract void setListener();

    @Override
    public void onClick(View view) {
    }

    /**
     * 结束刷新或加载更多
     *
     * @param refreshLayout
     */
    protected void stopRefreshing(CHZZRefreshLayout refreshLayout, CHZZLoadDataLayout loadLayout) {
        if (refreshLayout != null) {
            refreshLayout.endRefreshing();
            refreshLayout.endLoadingMore();
        }
        if (null != loadLayout) {
            mLoadLayout.setStatus(CHZZLoadDataLayout.DISMISS);
            loadLayout.setStatus(CHZZLoadDataLayout.SUCCESS);
        }
    }


    protected void initRefreshLayout(RecyclerView mDataRv, CHZZRefreshLayout mRefreshLayout, CommonRecyclerAdapter adapter, boolean loadMore) {
        CHZZMoocStyleRefreshViewHolder leftRefreshViewHolder = new CHZZMoocStyleRefreshViewHolder(mApp, loadMore);
        leftRefreshViewHolder.setSpringDistanceScale(2);
        //刷新图标
        leftRefreshViewHolder.setOriginalImage(R.mipmap.ic_launcher);
        //刷新头背景色
        leftRefreshViewHolder.setUltimateColor(R.color.white);
        //设置刷新头
        mRefreshLayout.setRefreshViewHolder(leftRefreshViewHolder);
        //布局管理器
        layoutManager = new LinearLayoutManager(mApp, GridLayoutManager.VERTICAL, false);
        if (null != mDataRv) {
            mDataRv.setLayoutManager(layoutManager);
            mDataRv.addItemDecoration(new CHZZDivider(mApp));
            mDataRv.setAdapter(adapter);
        }
    }

    @Override
    public void onCHZZRefreshLayoutBeginRefreshing(CHZZRefreshLayout refreshLayout) {
        ThreadUtil.runInUIThread(new Runnable() {
            @Override
            public void run() {
                pageIndex = 1;
                onUserVisible();
            }
        }, ConstantValues.LOADING_DURATION);
    }

    @Override
    public boolean onCHZZRefreshLayoutBeginLoadingMore(CHZZRefreshLayout refreshLayout) {
        if (pageIndex < pageCount) {
            ThreadUtil.runInUIThread(new Runnable() {
                @Override
                public void run() {
                    onUserVisible();
                }
            }, 200);
            return true;
        } else if (pageIndex > 2) {
            ToastUtil.show("暂无更多数据");
            return false;
        }
        return false;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void handleTop(final AppBarLayout appBar) {
        if (appBar != null) {
            mDataRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        int firstVisiblePosition = layoutManager.findFirstCompletelyVisibleItemPosition();
                        if (firstVisiblePosition == 0) {
                            appBar.setExpanded(true, true);
                        }
                    }
                }
            });
        }

    }


    public String getVersionCode(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        String versionCode = "";
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionCode = packageInfo.versionCode + "";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    public String getVersionName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        String versionName = "";
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionName = packageInfo.versionName + "";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

}

