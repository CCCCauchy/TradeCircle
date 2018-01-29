package com.xiaochui.tradecircle.ui.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.xiaochui.tradecircle.R;
import com.xiaochui.tradecircle.presenter.BasePresenter;
import com.xiaochui.tradecircle.ui.base.BaseActivity;
import com.xiaochui.tradecircle.ui.base.BaseFragment;
import com.xiaochui.tradecircle.ui.fragment.MainIndexFragment;
import com.xiaochui.tradecircle.ui.service.LocationService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class MainActivity extends BaseActivity<BasePresenter> implements BottomNavigationBar.OnTabSelectedListener {

    @BindView(R.id.activity_main_frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.activity_main_bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;

    private BaseFragment mTab1;
    private BaseFragment mTab2;
    private BaseFragment mTab3;
    private BaseFragment mTab4;
    final ExecutorService writeExecutor = Executors.newSingleThreadExecutor();
    int i;
    OkHttpClient client;
    Request request;

    ServiceConnection serviceConnection = new ServiceConnection() {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("sysout", "onServiceConnected");
            LocationService.LocationBinder binder = (LocationService.LocationBinder) service;
            binder.getService().startFroregroud();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("sysout", "onServiceDisconnected");
        }
    };

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseActivity getUi() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "发现"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "场所"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "消息"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "我的"))
                .setMode(BottomNavigationBar.MODE_DEFAULT)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.activity_main_frame_layout, mTab1= new MainIndexFragment());
        transaction.commit();


        //新建client


    }

    @Override
    public void onTabSelected(int position) {
        client = new OkHttpClient.Builder()
                .build();
        client.dispatcher().setMaxRequests(100);
        client.dispatcher().setMaxRequestsPerHost(100);
//构造request对象
        request = new Request.Builder()
                .url("ws://192.168.1.163:8989/")
                .build();
//new 一个websocket调用对象并建立连接

        for (i = 0; i < 100; i++) {
            writeExecutor.execute(new Thread() {
                @Override
                public void run() {
                    super.run();
                    client.newWebSocket(request, new WebSocketListener() {
                        WebSocket webSocket = null;

                        @Override
                        public void onOpen(final WebSocket webSocket, Response response) {
                            //保存引用，用于后续操作
                            this.webSocket = webSocket;
                            //打印一些内容
                            Log.w("sysout", "onOpen.." + "client request header:" + response.request().headers() + "\n" + "client response header:" + response.headers()
                                    + "\n" + "client response header:" + response.headers() + "\n" + "client response:" + response);
                            //注意下面都是write线程回写给客户端
                            //建立连接成功后，发生command 1给服务器端
                            writeExecutor.execute(new Runnable() {
                                @Override
                                public void run() {
                                    webSocket.send("command1");
                                }
                            });
                        }


                        @Override
                        public void onMessage(final WebSocket webSocket, String text) {
                            super.onMessage(webSocket, text);
                            Log.i("sysout", i + "onMessage.." + text);
                            if ("replay command 1".equals(text)) {
                                //收到服务器返回的replay command 1后继续向服务器端发送command 2
                                //replay it
                                writeExecutor.execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        webSocket.send("command2");
                                    }
                                });
                            }
                        }


                        @Override
                        public void onClosed(WebSocket webSocket, int code, String reason) {
                            super.onClosed(webSocket, code, reason);
                            Log.i("sysout", "onClosed.." + "code:" + code + " reason:" + reason);
                        }

                        @Override
                        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                            super.onFailure(webSocket, t, response);
                            Log.i("sysout", "onFailure..." + "throwable:" + t + "\nresponse:" + response);
                        }
                    });
                }
            });

        }

    }

    @Override
    public void onTabUnselected(int position) {
        bindService(new Intent(this, LocationService.class), serviceConnection, BIND_AUTO_CREATE);
    }

    @Override
    public void onTabReselected(int position) {
        unbindService(serviceConnection);
    }
}
