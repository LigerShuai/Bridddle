package com.liger.bridddle.utils;

import com.blankj.utilcode.util.SPStaticUtils;

/**
 * @author Liger
 * @date 2020/11/22 11:06
 */
public class Util {

    private static final String SP_TOKEN = "access_token";
    private static final String SP_TYPE = "token_type";

    public static boolean isLogin() {
        return !SPStaticUtils.getString(SP_TOKEN).isEmpty();
    }

    public static void saveLoginState(String token, String type) {
        SPStaticUtils.put(SP_TOKEN, token);
        SPStaticUtils.put(SP_TYPE, type);
    }

    public static String getHeader() {
        return SPStaticUtils.getString(SP_TYPE) + " " + SPStaticUtils.getString(SP_TOKEN);
    }

}
