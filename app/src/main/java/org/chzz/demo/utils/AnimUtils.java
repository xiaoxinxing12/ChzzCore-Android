package org.chzz.demo.utils;

import android.view.View;
import android.view.animation.RotateAnimation;

/**
 * Created by copy on 2017/10/25.
 */

public class AnimUtils {

    public static void showAnimation(View mView, int startDegress, int degrees)
    {
        float centerX = mView.getWidth() / 2.0f;
        float centerY = mView.getHeight() / 2.0f;
        //这个是设置需要旋转的角度（也是初始化），我设置的是当前需要旋转的角度
        RotateAnimation rotateAnimation = new RotateAnimation(startDegress,degrees,centerX,centerY);//centerX和centerY是旋转View时候的锚点
        //这个是设置动画时间的
        rotateAnimation.setDuration(10);
        //动画执行完毕后是否停在结束时的角度上
        rotateAnimation.setFillAfter(true);
        //启动动画
        mView.startAnimation(rotateAnimation);
    }
}
