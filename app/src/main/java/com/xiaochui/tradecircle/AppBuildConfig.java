package com.xiaochui.tradecircle;

/**
 * @author Death丶Love
 * @project_name XiaoChuiProject
 * @package_name com.xiaochui.mainlibrary
 * @time 2017/7/25 10:12
 * @remark
 */

public class AppBuildConfig {
    /**
     * 上线准备：
     * 1.修改 build.gradle 文件中versionCode，versionName值
     * 2.修改 BuildConfig 文件中 VERSION_NAME 值
     * 3.修改 BuildConfig 文件中 DEBUG 值
     * 4.修改 baseUrl 值，切换到线上服务器
     */
    /**
     * 是否是调试模式
     * 是否可以打印 log
     */
    public static final boolean DEBUG = true;
    /**
     * 版本号
     * 网络请求头之一
     */
    public static final String VERSION_NAME = "0.0.1";
    /**
     * 使用平台 Android端 1
     */
    public static final String PLATFORM = "1";
}
