package com.jingcai.jc_11x5.entity;

import android.text.TextUtils;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.jingcai.jc_11x5.util.DateUtil;
import com.jingcai.jc_11x5.util.MathUtil;
import com.jingcai.jc_11x5.util.Ren5ExcludeUtil;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/4/11.
 */

@DatabaseTable(tableName="jc-11x5_sys_kaijiang")
public class KaiJiang_11x5 implements Serializable, Comparable<KaiJiang_11x5> {

    @DatabaseField(id=true, columnName="orderNum")
    private String orderNum;//期号 帶年月日  17051184
    @DatabaseField(columnName="luckyNumber")
    private String luckyNumber;//开奖号
    @DatabaseField(columnName="luckyNumberSort")
    private String luckyNumberSort;//开奖号排序
    @DatabaseField(columnName="caiZhong")
    private String caiZhong;//彩种
    @DatabaseField(columnName="time")
    private String time;

    @DatabaseField(columnName="hezhi")
    private String hezhi; //和值
    @DatabaseField(columnName="kuadu")
    private String kuadu; //跨度
    @DatabaseField(columnName="chonghaoshu")
    private String chonghaoshu; //重复数
    @DatabaseField(columnName="daXiaoBi")
    private String daXiaoBi; //大小比
    @DatabaseField(columnName="jiOuBi")
    private String jiOuBi; //奇偶比
    @DatabaseField(columnName="zhiHeBi")
    private String zhiHeBi; //质合比
    @DatabaseField(columnName="lubi")
    private String lubi; //012路比
    @DatabaseField(columnName="linhaogeshu")
    private String linhaogeshu; //邻号个数
    @DatabaseField(columnName="pingjunzhi")
    private String pingjunzhi; //平均值
    @DatabaseField(columnName="zouShi")
    private String zouShi; //走势

    private String[] num;

    public KaiJiang_11x5() {
    }

    public KaiJiang_11x5(String orderNum, String luckyNumber) {
        this.orderNum = orderNum;
        this.luckyNumber = luckyNumber;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getLuckyNumber() {
        return luckyNumber;
    }

    public void setLuckyNumber(String luckyNumber) {
        this.luckyNumber = luckyNumber;
    }

    public String getLuckyNumberSort() {
        return luckyNumberSort;
    }

    public void setLuckyNumberSort(String luckyNumberSort) {
        this.luckyNumberSort = luckyNumberSort;
    }

    public String getCaiZhong() {
        return caiZhong;
    }

    public void setCaiZhong(String caiZhong) {
        this.caiZhong = caiZhong;
    }

    public String getHezhi() {
        return hezhi;
    }

    public void setHezhi(String hezhi) {
        this.hezhi = hezhi;
    }

    public String getKuadu() {
        return kuadu;
    }

    public void setKuadu(String kuadu) {
        this.kuadu = kuadu;
    }

    public String getChonghaoshu() {
        return chonghaoshu;
    }

    public void setChonghaoshu(String chonghaoshu) {
        this.chonghaoshu = chonghaoshu;
    }

    public String getDaXiaoBi() {
        return daXiaoBi;
    }

    public void setDaXiaoBi(String daXiaoBi) {
        this.daXiaoBi = daXiaoBi;
    }

    public String getJiOuBi() {
        return jiOuBi;
    }

    public void setJiOuBi(String jiOuBi) {
        this.jiOuBi = jiOuBi;
    }

    public String getZhiHeBi() {
        return zhiHeBi;
    }

    public void setZhiHeBi(String zhiHeBi) {
        this.zhiHeBi = zhiHeBi;
    }

    public String getLubi() {
        return lubi;
    }

    public void setLubi(String lubi) {
        this.lubi = lubi;
    }

    public String getLinhaogeshu() {
        return linhaogeshu;
    }

    public void setLinhaogeshu(String linhaogeshu) {
        this.linhaogeshu = linhaogeshu;
    }

    public String getPingjunzhi() {
        return pingjunzhi;
    }

    public void setPingjunzhi(String pingjunzhi) {
        this.pingjunzhi = pingjunzhi;
    }

    public String getZouShi() {
        return zouShi;
    }

    public void setZouShi(String zouShi) {
        this.zouShi = zouShi;
    }

    public String[] getNum() {
        if(luckyNumber.equals("等待开奖中")){
            return null;
        }
        if((num == null || num.length <1) && (luckyNumber != null && !TextUtils.isEmpty(luckyNumber))){
            this.num = luckyNumber.split(" ");
            Arrays.sort(this.num);
        }
        return num;
    }

    public void initValue(){
        this.num = getNum();
        if(num == null){
            return;
        }
        if(orderNum != null && !TextUtils.isEmpty(orderNum)){
            String year = "20" + orderNum.substring(0,2);
            String month = orderNum.substring(2,4);
            String day = orderNum.substring(4,6);
            this.time = year + "-" + month + "-" + day;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for(int i=0; i < num.length; i++){
            if(i==num.length-1){
                stringBuffer.append(num[i]);
            }else{
                stringBuffer.append(num[i]).append(" ");
            }
        }
        this.luckyNumberSort = stringBuffer.toString();
        //和值
        if(hezhi == null || TextUtils.isEmpty(hezhi)){
            int hz = 0;
            for(String str : num){
                hz += Integer.parseInt(str);
            }
            this.hezhi = String.valueOf(hz);
        }
        //跨度
        if(kuadu == null || TextUtils.isEmpty(kuadu)){
            this.kuadu = String.valueOf(Integer.parseInt(num[num.length-1]) - Integer.parseInt(num[0]));
        }
        //重号个数

        //大小比
        if(daXiaoBi == null || TextUtils.isEmpty(daXiaoBi)){
            int da = 0;
            int xiao = 0;
            for(String str : num){
                if(Integer.parseInt(str)<6){
                    xiao+=1;
                }else{
                    da+=1;
                }
            }
            this.daXiaoBi =   da+":"+xiao;
        }
        //奇偶比
        if(jiOuBi == null || TextUtils.isEmpty(jiOuBi)){
            int ji = 0;
            int ou = 0;
            for(String str : num){
                if(Integer.parseInt(str)%2==0){
                    ou+=1;
                }else{
                    ji+=1;
                }
            }
            this.jiOuBi =  ji+":"+ou;
        }
        //质合比
        if(zhiHeBi == null || TextUtils.isEmpty(zhiHeBi)){
            int zs = 0;
            int hs = 0;
            for(String str : num){
                int num = Integer.parseInt(str);
                if(MathUtil.isZhishu(num)){
                    zs+=1;
                }else{
                    hs+=1;
                }
            }
            this.zhiHeBi =  zs+":"+hs;
        }
        //012路比
        if(lubi == null || TextUtils.isEmpty(lubi)){
            int lu0 = 0;
            int lu1 = 0;
            int lu2 = 0;
            for(String str : num){
                if(Integer.parseInt(str)%3==0){
                    lu0+=1;
                }else if(Integer.parseInt(str)%3==1){
                    lu1+=1;
                }else{
                    lu2+=1;
                }
            }
            this.lubi = lu0+":"+lu1+":"+lu2;
        }
        //邻号数

        //平均值
        if(pingjunzhi == null || TextUtils.isEmpty(pingjunzhi)){
            if(hezhi != null && !TextUtils.isEmpty(hezhi)){
                this.pingjunzhi = String.valueOf(Math.round(Float.parseFloat(hezhi)/5));
            }
        }
    }

    /**
     * 重号数
     * @param numLast
     */
    public void initChonghaoshu(String[] numLast){
        if(num == null || numLast == null){
            return;
        }
        List<String> numList = Arrays.asList(num);
        List<String> numLastList = Arrays.asList(numLast);
        int chongfushu = 0;
        for(int j = 0; j < numList.size(); j++){
            boolean isBh = numLastList.contains(numList.get(j).trim());
            if(isBh){
                chongfushu += 1;
            }
        }
        this.chonghaoshu = String.valueOf(chongfushu);
    }

    /**
     * 邻号个数
     * @param numLast
     */
    public void initLinHaoGeshu(String numLast){
        if(num == null || numLast == null){
            return;
        }
        String linhao = Ren5ExcludeUtil.jisuanLinhao(numLast);
        int lihaoCount = 0;
        for (int i = 0; i < num.length; i++) {
            if (linhao.indexOf(num[i]) >= 0) {
                lihaoCount++;
            }
        }
        this.linhaogeshu = String.valueOf(lihaoCount);
    }

    /**
     * 开奖走势
     * @param lastZoushi
     * @param lastNum
     */
    public void initZouShi(String lastZoushi, String[] lastNum){
        if(num == null){
            return;
        }
        String[] arrayZouShi = new String[]{"1","1","1","1","1","1","1","1","1","1","1"};
        List<String> zoushiList = Arrays.asList(arrayZouShi);

        if(lastZoushi == null || TextUtils.isEmpty(lastZoushi)){
            for(String str : num){
                int i = Integer.parseInt(str);
                arrayZouShi[i-1] = str;
            }
        } else {
            String[] arrayLastZoushi = lastZoushi.split(",");
            List<String> lastzoushiList = Arrays.asList(arrayLastZoushi);
            for(int i = 0; i < lastzoushiList.size(); i++){
                String zoushiStr = lastzoushiList.get(i);
                int zoushi = Integer.parseInt(zoushiStr);
                boolean isWinNum = false;//是否是幸运数字
                for(int j=0; j< num.length; j++){
                    String wnumStr = num[j];
                    int wnum = Integer.parseInt(wnumStr);
                    if(i+1 == wnum){
                        isWinNum = true;
                        zoushiList.set(i, wnumStr);
                        break;
                    }
                }
                boolean isContain = false;
                if(lastNum != null){
                    for(int m = 0; m < lastNum.length; m++){
                        if(zoushi == Integer.parseInt(lastNum[m])){
                            isContain = true;
                            break;
                        }
                    }
                }
                if(!isWinNum){
                    if(zoushi - 1 == i && isContain){
                        zoushiList.set(i, "1");
                    }else{
                        zoushiList.set(i, String.valueOf(zoushi+1));
                    }
                }
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        int size = arrayZouShi.length;
        for(int i=0; i<size;i++){
            if(i == size-1){
                stringBuilder.append(arrayZouShi[i]);
            }else {
                stringBuilder.append(arrayZouShi[i]).append(",");
            }
        }
        this.zouShi = stringBuilder.toString();
    }

    @Override
    public int compareTo(KaiJiang_11x5 o) {
        int i = Integer.parseInt(this.getOrderNum()) - Integer.parseInt(o.getOrderNum());
        return i;
    }
}
