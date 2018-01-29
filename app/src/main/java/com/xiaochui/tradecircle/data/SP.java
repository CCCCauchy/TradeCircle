package com.xiaochui.tradecircle.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;


/**
 * @author Death丶Love
 * @project_name XiaoChuiProject
 * @package_name com.xiaochui.mainlibrary.utils
 * @time 2017/7/27 10:55
 * @remark SharePreference基本数据存储
 */

public class SP {

    /**
     * 文件名
     */
    public static String FILE_NAME = "xiaochui_share_data";

    private static String USERTOKEN = "user_token";
    private static String ISLOGIN = "is_login";
    private static String ISINTERESTCHOOSED = "is_interest_choosed";
    private static String APPVERSIONCODE = "app_versioncode";
    private static String USERSECRET = "user_secret";
    private static String USERTELEPHONE = "user_telephone";
    private static String USERNAME = "user_name";
    private static String USERPORTRAITURL = "user_portraiturl";
    private static String USERID = "user_id";
    private static String USERPASSWORD = "user_password";
    private static String USERGENDER = "user_gender";
    private static String USEREMAIL = "user_email";
    private static String USERUUKEY = "user_uukey";
    private static String USERCITY = "user_city";
    private static String USERWORKPLACE = "user_work_place";
    private static String SERVICECHANNEL = "service_channel";
    private static String TEACHERCHANNEL = "teacher_channel";
    private static String VOICEHELPER = "voice_helper";

    /**
     * 保存数据, 根据具体的数据类型保存数据
     */
    public static void put(Context ctx, String key, Object obj) {
        SharedPreferences sp = ctx.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if (obj instanceof String) {
            editor.putString(key, (String) obj);
        } else if (obj instanceof Integer) {
            editor.putInt(key, (Integer) obj);
        } else if (obj instanceof Boolean) {
            editor.putBoolean(key, (Boolean) obj);
        } else if (obj instanceof Float) {
            editor.putFloat(key, (Float) obj);
        } else if (obj instanceof Long) {
            editor.putLong(key, (Long) obj);
        } else {
            editor.putString(key, obj.toString());
        }
        editor.apply();
        editor.commit();
    }

    /**
     * 获取保存的数据,根据默认值的数据类型调用方法
     */
    @NonNull
    public static Object get(Context ctx, String key, Object defaultObj) {
        SharedPreferences sp = ctx.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        if (defaultObj instanceof String) {
            return sp.getString(key, (String) defaultObj);
        } else if (defaultObj instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObj);
        } else if (defaultObj instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObj);
        } else if (defaultObj instanceof Float) {
            return sp.getFloat(key, (Float) defaultObj);
        } else if (defaultObj instanceof Long) {
            return sp.getLong(key, (Long) defaultObj);
        }
        return null;
    }

    public static void saveLogin(Context context) {
        SP.put(context, ISLOGIN, true);
    }

    public static void removeLogin(Context context) {
        SP.put(context, ISLOGIN, false);
    }

    public static void setInterestChoosed(Context context, boolean isChoosed) {
        SP.put(context, ISINTERESTCHOOSED, isChoosed);
    }

    public static boolean isInterestChoosed(Context context) {
        return (boolean) SP.get(context, ISINTERESTCHOOSED, false);
    }

    public static boolean getLogin(Context context) {
        return (boolean) SP.get(context, ISLOGIN, false);
    }

    public static void saveVersionCode(Context context, int versionCode) {
        SP.put(context, APPVERSIONCODE, versionCode);
    }

    public static int getVersionCode(Context context) {
        return (int) SP.get(context, APPVERSIONCODE, 0);
    }

    public static String getUserSecret(Context context) {
        return (String) SP.get(context, USERSECRET, "");
    }

    public static String getToken(Context context) {
        if (SP.get(context, USERTOKEN, "") != null) {
            return (String) SP.get(context, USERTOKEN, "");
        } else {
            return null;
        }
    }

    public static void saveUserTelephone(Context context, String phone) {
        SP.put(context, USERTELEPHONE, notNull(phone + ""));
    }

    public static void saveUserPortraiturl(Context context, String url) {
        SP.put(context, USERPORTRAITURL, notNull(url));
    }

    public static void clearUser(Context context) {
        try {
            SP.put(context, USERSECRET, "");
            SP.put(context, USERTOKEN, "");
            SP.put(context, USERID, "");
            SP.put(context, USERTELEPHONE, "");
            SP.put(context, USERNAME, "");
            SP.put(context, USERPORTRAITURL, "");
            SP.put(context, USERPASSWORD, "");
            SP.put(context, USERGENDER, 1);
            SP.put(context, USEREMAIL, "");
            SP.put(context, USERCITY, "");
            SP.put(context, USERWORKPLACE, "");
        } catch (Exception e) {

        }
    }

    public static String getUserId(Context context) {
        return (String) SP.get(context, USERID, "");
    }

    public static String getUserTelephone(Context context) {
        return (String) SP.get(context, USERTELEPHONE, "");
    }

    public static String getUserName(Context context) {
        return (String) SP.get(context, USERNAME, "");
    }

    public static String getUserPassword(Context context) {
        return (String) SP.get(context, USERPASSWORD, "");
    }

    public static String getUserPortraiturl(Context context) {
        return (String) SP.get(context, USERPORTRAITURL, "");
    }

    public static Integer getUserGender(Context context) {
        return (Integer) SP.get(context, USERGENDER, 1);
    }

    public static String getUserEmail(Context context) {
        return (String) SP.get(context, USEREMAIL, "");
    }

    public static int getUserUUKey(Context context) {
        return (int) SP.get(context, USERUUKEY, 0);
    }

    public static String getUserCity(Context context) {
        return (String) SP.get(context, USERCITY, "");
    }

    public static String getUserWorkPlace(Context context) {
        return (String) SP.get(context, USERWORKPLACE, "");
    }

    public static void setServiceChannel(Context context, boolean isFirst) {
        SP.put(context, SERVICECHANNEL, isFirst);
    }

    public static boolean getServiceChannel(Context context) {
        return ((boolean) SP.get(context, SERVICECHANNEL, true));
    }

    public static void setTeacherChannel(Context context, boolean isFirst) {
        SP.put(context, TEACHERCHANNEL, isFirst);
    }

    public static boolean getTeacherChannel(Context context) {
        return ((boolean) SP.get(context, TEACHERCHANNEL, true));
    }

    public static void setVoiceHelperEnable(Context context, boolean isEnable) {
        SP.put(context, VOICEHELPER, isEnable);
    }

    public static boolean getVoiceHelperEnable(Context context) {
        return (boolean) SP.get(context, VOICEHELPER, false);
    }

    public static String notNull(String obj) {
        return obj != null ? obj : "";
    }
}
