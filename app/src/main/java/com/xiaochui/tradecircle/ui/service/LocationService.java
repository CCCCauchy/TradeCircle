package com.xiaochui.tradecircle.ui.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.widget.RemoteViews;

import com.xiaochui.tradecircle.R;
import com.xiaochui.tradecircle.ui.activity.MainActivity;

public class LocationService extends Service {

    private LocationBinder binder = new LocationBinder();

    public LocationService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        stopForeground(true);
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void startFroregroud() {
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_offline_notification);
        Intent intent = new Intent(this, MainActivity.class);
        intent.setAction("adasdfsadfsaffdafdsdfddfsdfsdfdfsfds");
        int flag = PendingIntent.FLAG_UPDATE_CURRENT;
        PendingIntent clickIntent = PendingIntent.getActivity(this, 1, intent, flag );
        remoteViews.setOnClickPendingIntent(R.id.layout_offline_notification_btn,clickIntent);
        Intent nfIntent = new Intent(this, MainActivity.class);
        Notification notification =
                notification = new Notification.Builder(this).setContentIntent(PendingIntent.
                        getActivity(this, 0, nfIntent, 0)) // 设置PendingIntent
                        .setLargeIcon(BitmapFactory.decodeResource(this.getResources(),
                                R.mipmap.ic_launcher)) // 设置下拉列表中的图标(大图标)
                        .setContentTitle("上班中") // 设置下拉列表里的标题
                        .setSmallIcon(R.mipmap.ic_launcher) // 设置状态栏内的小图标
                        .setContentText("") // 设置上下文内容
                        .setWhen(System.currentTimeMillis()).build(); // 设置该通知发生的时间
        notification.contentView = remoteViews;
        startForeground(110, notification);// 开始前台服务
    }

    public class LocationBinder extends Binder {

        public LocationService getService() {
            return LocationService.this;
        }
    }
}
