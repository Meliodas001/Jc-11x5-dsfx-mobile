package com.jingcai.jc_11x5.util;

import android.os.Handler;
import android.os.Message;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpUtil {

    private HashMap<String, String> map;
    private String strUrl;
    private HttpGet httpGet;
    private HttpClient client;
    private HttpPost post;
    private Handler handler;

    public HttpUtil(HashMap<String, String> map,String strUrl, Handler handler)
    {
        this.map = map;
        this.strUrl = strUrl;
        this.handler = handler;
    }

    public void ExecuteGetRequest() {

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    String result = "";
                    Iterator iterator = map.entrySet().iterator();
                    while(iterator.hasNext())
                    {
                        Map.Entry entry = (Map.Entry)iterator.next();
                        String key = entry.getKey().toString();
                        String value = entry.getValue().toString();
                        strUrl += key+"="+value+"&";
                    }
                    strUrl = strUrl.substring(0, strUrl.length() - 1);
                    httpGet = new HttpGet(strUrl);
                    client = new DefaultHttpClient();
                    HttpResponse response = client.execute(httpGet);
                    if(response.getStatusLine().getStatusCode()==200){
                        result = EntityUtils.toString(response.getEntity());
                    }
                    Message msg = new Message();
                    msg.obj = result;
                    handler.sendMessage(msg);
                } catch (ClientProtocolException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public void ExecutePostRequest()
    {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                String result = "";
                List<NameValuePair> list = new ArrayList<NameValuePair>();
                try {
                    post = new HttpPost(strUrl);
                    client = new DefaultHttpClient();
                    Iterator iterator = map.entrySet().iterator();
                    while(iterator.hasNext())
                    {
                        Map.Entry entry = (Map.Entry)iterator.next();
                        String key = entry.getKey().toString();
                        String value = entry.getValue().toString();
                        NameValuePair nv = new BasicNameValuePair(key, value);
                        list.add(nv);
                    }
                    post.setEntity(new UrlEncodedFormEntity(list, HTTP.UTF_8));
                    HttpResponse response = client.execute(post);
                    if(response.getStatusLine().getStatusCode() == 200)
                    {
                        result = EntityUtils.toString(response.getEntity());
                    }
                    Message msg = new Message();
                    msg.obj = result;
                    handler.sendMessage(msg);
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (ClientProtocolException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        });
        thread.start();

    }
}
