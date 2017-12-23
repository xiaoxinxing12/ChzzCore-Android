package org.chzz.demo.utils;


import android.content.Context;

import org.chzz.core.util.ToastUtil;

public class CheckUtils {

    /**
     * @param text
     * @return false 不为空空
     **/
    public static boolean checkEmpty(String text) {
        if (text == null || "".equals(text) || text.isEmpty() || text.length() <= 0) {
            return true;
        }
        return false;
    }

    public static boolean checkEmpty(String text, Context context, String msg) {
        if (text == null || "".equals(text) || text.isEmpty() || text.length() <= 0) {
            ToastUtil.show(msg);
            return true;
        }
        return false;
    }


}
