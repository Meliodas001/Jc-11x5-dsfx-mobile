package com.jingcai.jc_11x5.business.impl;

import android.os.Handler;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.jingcai.jc_11x5.app.App;
import com.jingcai.jc_11x5.consts.InterfaceConsts;
import com.jingcai.jc_11x5.entity.Lottery;
import org.java_websocket.enums.ReadyState;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class WebSocketServer{

    public static JWebSocketClient client;
    /**
     * 初始化websocket连接
     */
    public static void getSocketClient() {
        String id = App.getInstance().getUser().getId();
        URI uri = URI.create(InterfaceConsts.WS_URL + id);
        client = new JWebSocketClient(uri) {

            @Override
            public void onMessage(String message) {
//                Log.e("JWebSocketClientService", "收到的消息：" + message);
                App app = App.getInstance();
                Map mapResult = JSON.parseObject(message, HashMap.class);
                Map map = JSON.parseObject(mapResult.get(app.getUser().getCaizhong()).toString(), HashMap.class);
                Lottery lottery = new Lottery(map, app.getUser().getCaizhong());
                if (app.getLottery() != null) {
                    if (lottery.getCaiNumber().equals(app.getLottery().getCaiNumber()))
                        lottery.setNew(false);
                    else
                        lottery.setNew(true);
                } else {
                    lottery.setNew(false);
                }
                App.getInstance().setLottery(lottery);
            }

            @Override
            public void onOpen(ServerHandshake handshakedata) {
                super.onOpen(handshakedata);
                Log.e("JWebSocketClientService", "websocket连接成功");
            }
        };
        connect();
    }

    /**
     * 连接websocket
     */
    private static void connect() {
        new Thread() {
            @Override
            public void run() {
                try {
                    //connectBlocking多出一个等待操作，会先连接再发送，否则未连接发送会报错
                    client.connectBlocking();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    /**
     * 发送消息
     *
     * @param type
     */
    public static void sendMsg(String type) {
        while (!client.getReadyState().equals(ReadyState.OPEN)) {
            Log.e("连接中···请稍后", "");
        }
        if (null != client) {
//            Log.e("JWebSocketClientService", "发送的消息：" + msg);
            if (type == null)
                type = App.getInstance().getUser().getCaizhong();
            client.send(type);
        }
    }

    public static void getConnection() {
        mHandler.postDelayed(heartBeatRunnable, HEART_BEAT_RATE);//开启心跳检测
    }

    private static final long HEART_BEAT_RATE = 10 * 1000;//每隔10秒进行一次对长连接的心跳检测
    private static Handler mHandler = new Handler();
    private static Runnable heartBeatRunnable = new Runnable() {
        @Override
        public void run() {
//            Log.e("JWebSocketClientService", "开启心跳检测");
            if (client != null) {
                if (client.isClosed()) {
                    reconnectWs();
                }
            } else {
                //如果client已为空，重新初始化websocket
                getSocketClient();
            }
            //定时对长连接进行心跳检测
            mHandler.postDelayed(this, HEART_BEAT_RATE);
        }
    };

    /**
     * 开启重连
     */
    private static void reconnectWs() {
        mHandler.removeCallbacks(heartBeatRunnable);
        new Thread() {
            @Override
            public void run() {
                try {
                    //重连
                    client.reconnectBlocking();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
