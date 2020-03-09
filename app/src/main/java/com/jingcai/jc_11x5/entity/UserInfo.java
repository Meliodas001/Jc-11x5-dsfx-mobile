package com.jingcai.jc_11x5.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/5.
 */

public class UserInfo implements Serializable {

    private String Id;
    private String UserName;//用户名
    private String NickName;//昵称
    private String Mac;//机器标识
    private String UserCover;//简历
    private String Balance;//点币
    private String Coin;//积分
    private String money; //余额
    private String PassWord;//密码
    private String CreateTime;//创建日期
    private boolean Enabled;//启用
    private String ShareNo;//6位推广号
    private String token; //登录token
    private boolean vip;
    private String vipEndData;
    private Integer news;

    public Integer getNews() {
        return news;
    }

    public void setNews(Integer news) {
        this.news = news;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public String getVipEndData() {
        return vipEndData;
    }

    public void setVipEndData(String vipEndData) {
        this.vipEndData = vipEndData;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    private String msgCode; //短信验证码

    private String caizhong;//当前选择的猜
    private String caizhongMc;
    private String integral;
    private String game;

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public UserInfo(){}

    public UserInfo(String mac) {
        Mac = mac;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getMac() {
        return Mac;
    }

    public void setMac(String mac) {
        Mac = mac;
    }

    public String getUserCover() {
        return UserCover;
    }

    public void setUserCover(String userCover) {
        UserCover = userCover;
    }

    public String getBalance() {
        return Balance;
    }

    public void setBalance(String balance) {
        Balance = balance;
    }

    public String getCoin() {
        return Coin;
    }

    public void setCoin(String coin) {
        Coin = coin;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public boolean isEnabled() {
        return Enabled;
    }

    public void setEnabled(boolean enabled) {
        Enabled = enabled;
    }

    public String getShareNo() {
        return ShareNo;
    }

    public void setShareNo(String shareNo) {
        ShareNo = shareNo;
    }

    public String getCaizhong() {
        return caizhong;
    }

    public void setCaizhong(String caizhong) {
        this.caizhong = caizhong;
    }

    public String getCaizhongMc() {
        return caizhongMc;
    }

    public void setCaizhongMc(String caizhongMc) {
        this.caizhongMc = caizhongMc;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
