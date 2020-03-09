package com.jingcai.jc_11x5.business;

import com.alibaba.fastjson.JSONObject;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by yangsen on 2016/7/29.
 */
public class WebServiceRequester {

    public static JSONObject callWebService(String webServerUrl, String nameSpace, String methodName, DealParam dealParam){
        JSONObject jsonObject = new JSONObject();
        // 创建HttpTransportSE对象，传递WebService服务器地址
        final HttpTransportSE httpTransportSE = new HttpTransportSE(webServerUrl);
        // 创建SoapObject对象
        SoapObject soapObject = new SoapObject(nameSpace, methodName);
        soapObject = dealParam.dealparam(soapObject);
        // 实例化SoapSerializationEnvelope，传入WebService的SOAP协议的版本号
        final SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.setOutputSoapObject(soapObject);
        soapEnvelope.dotNet = true;
        httpTransportSE.debug = true;
        int ztdm = 0;
        SoapObject resultSoapObject = null;
        try {
            httpTransportSE.call(nameSpace +"IService1/"+ methodName, soapEnvelope);
            if (soapEnvelope.getResponse() != null) {
                // 获取服务器响应返回的SoapObject
                resultSoapObject = (SoapObject) soapEnvelope.bodyIn;
                if(resultSoapObject != null){
                    ztdm = 1;
                    String result = resultSoapObject.getProperty(0).toString();
                    if(result == null || result.equals("anyType{}")){
                        result = "";
                    }
                    jsonObject.put("result", result);
                }
            }
        } catch (Exception e) {
            ztdm = 0;
            e.printStackTrace();
        }
        jsonObject.put("ztdm",ztdm);
        return jsonObject;
    }

    DealParam dealParam;

    public DealParam getDealParam() {
        return dealParam;
    }

    public void setDealParam(DealParam dealParam) {
        this.dealParam = dealParam;
    }

    public interface DealParam{
        SoapObject dealparam(SoapObject soapObject);
    }



    static String NameSpace = "http://tempuri.org/";
    static String URL="http://39.108.153.232:8732/Service1/";
    static String SOAP_ACTION="http://tempuri.org/IService1/CreateUserInfo";
    static String MethodName="CreateUserInfo";

    public static void CallWebService(){
        //指定的命名空间和调用的方法名
        SoapObject request = new SoapObject(NameSpace, MethodName);

        //设置需调用接口需要传入的参数
        request.addProperty("phone", "imoonstal");
        request.addProperty("mac", "imoonstal");
        request.addProperty("nickName", "imoonstal");
        request.addProperty("ps", "imoonstal");

        // 生成调用方法的SOAP请求信息,并指定SOAP的版本
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);

        // 下面这两句是一样的作用，写一句就行了
        envelope.bodyOut = request;
        envelope.setOutputSoapObject(request);

        // 设置是否调用的是dotNet开发的WebService
        envelope.dotNet = true;

        HttpTransportSE transport = new HttpTransportSE(URL);
        try {
            // 调用
            transport.call(SOAP_ACTION, envelope);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 获取返回的数据
        SoapObject object = (SoapObject) envelope.bodyIn;
        if(null==object){
            return;
        }
        // 获取返回的结果
        String result = object.getProperty(0).toString();
        System.out.println(result);
    }


}


