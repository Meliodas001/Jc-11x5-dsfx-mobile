package com.jingcai.jc_11x5.business.impl;

import android.text.TextUtils;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.jingcai.jc_11x5.app.App;
import com.jingcai.jc_11x5.consts.InterfaceConsts;
import com.jingcai.jc_11x5.entity.Lottery;
import com.jingcai.jc_11x5.util.DateUtil;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class WebSocketServer{

    public JWebSocketClient client;
    /**
     * 初始化websocket连接
     */
    public void initSocketClient() {
        URI uri = URI.create(InterfaceConsts.WS_URL + App.getInstance().getUser().getId());
        client = new JWebSocketClient(uri) {

            @Override
            public void onMessage(String message) {
//                Log.e("JWebSocketClientService", "收到的消息：" + message);
                Lottery lottery = App.getInstance().getLottery();
                Map map = JSON.parseObject(JSON.parseObject(message).getString(App.getInstance().getUser().getCaizhong()), HashMap.class);
                lottery.setCaiQishu(map.get("issue").toString());
                String num = map.get("code").toString();
                lottery.setKjsj(DateUtil.formatToStr1((Long) map.get("createTime")));
                lottery.setJssj(DateUtil.formatToStr1((Long) map.get("endtime")));
                lottery.setCount(map.get("number").toString());
                lottery.setCaiNumber(num);
                if (num != null && !TextUtils.isEmpty(num)) {
                    lottery.setCaiNumArray(num.split(" "));
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
    private void connect() {
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
}
