package com.jingcai.jc_11x5.util;

import com.jingcai.jc_11x5.entity.KaiJiang_11x5;

import java.util.ArrayList;
import java.util.List;

/**
 * author：jyl
 * 号码过滤用
 */

public class Ren5ExcludeUtil {

    /*
 * String[] sl  ：要校验的号码
 * CheckNums[] baseNums  ：选中的基础号码
* */
    public static boolean BaseNumVerify(String[] sl, String[] baseNums) {
        int hezs = 0; //计算出了几个
        for (int i = 0; i < sl.length; i++) {
            boolean result = isExist(sl[i], baseNums);
            if(!result){
                return false;
            }
        }
        return true;
    }

    private static boolean isExist(String str, String[] baseNums){
        boolean result = false;
        for (int j = 0; j < baseNums.length; j++) {
            if (Integer.parseInt(str) == Integer.parseInt(baseNums[j])) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param sl ：要校验的号码
     * @param checkNums ：要校验的号码
     * @param disCount ：胆码出几个
     * @return
     */
    public static boolean DanZuVerify(String[] sl, String[] checkNums, Integer[] disCount) {
        if (checkNums == null || disCount == null) {
            return true;
        }
        int hezs = 0; //计算出了几个
        for (int i = 0; i < sl.length; i++) {
            for (int j = 0; j < checkNums.length; j++) {
                if (Integer.parseInt(sl[i]) == Integer.parseInt(checkNums[j])) {
                    hezs++;
                }
            }
        }
        for (int i = 0; i < disCount.length; i++) {
            if (disCount[i] == hezs) {
                return true;
            }
        }
        return false;
    }

    /*
     * String[] sl  ：要校验的号码
     * int[] sums ：选中的和值
    * */
    public static boolean SumValueVerify(String[] sl, Integer[] sums) {
        int hezs = 0; //计算和值
        for (int i = 0; i < sl.length; i++) {
            hezs += Integer.valueOf(sl[i]);
        }
        for (int i = 0; i < sums.length; i++) {
            if (sums[i] == hezs) {
                return true;
            }
        }
        return false;
    }

    /*
     * String[] sl  ：要校验的号码
     * int[] sumWeis ：选中的和值尾数
    * */
    public static boolean HeWeiValueVerify(String[] sl, Integer[] sumWeis) {
        int hezs = 0; //计算和值
        for (int i = 0; i < sl.length; i++) {
            hezs += Integer.valueOf(sl[i]);
        }
        String hw = String.valueOf(hezs).substring(String.valueOf(hezs).length() - 1, String.valueOf(hezs).length());
        int hzw = Integer.valueOf(hw);
        for (int i = 0; i < sumWeis.length; i++) {
            if (sumWeis[i] == hzw) {
                return true;
            }
        }
        return false;
    }

    /*
     * String[] sl  ：要校验的号码
     * int[] kd ：选中的跨度
    * */
    public static boolean KuaduVerfy(String[] sl, Integer[] kd) {
        int thisKuadu = Integer.valueOf(sl[sl.length - 1]) - Integer.valueOf(sl[0]);
        for (int i = 0; i < kd.length; i++) {
            if (kd[i] == thisKuadu) {
                return true;
            }
        }
        return false;
    }

    /*
     * String[] sl  ：要校验的号码
     * String[] hez ：选中的奇数个数 比例
    * */
    public static boolean JiShuVerify(String[] sl, String[] jh) {
        {
            int hezs = 0; //计算奇数个数
            for (int i = 0; i < sl.length; i++) {
                if (sl[i].equals("01") || sl[i].equals("03") || sl[i].equals("05") || sl[i].equals("07") || sl[i].equals("09") || sl[i].equals("11")) {
                    hezs++;
                }
            }
            for (int i = 0; i < jh.length; i++) {
                if (jh[i].indexOf(String.valueOf(hezs)) == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
     * String[] sl  ：要校验的号码
     * String[] jh ：选中的质数个数 比例
    * */
    public static boolean ZhiHaoVerify(String[] sl, String[] jh) {
        int hezs = 0; //计算质数个数
        for (int i = 0; i < sl.length; i++) {
            if (sl[i].equals("01") || sl[i].equals("02") || sl[i].equals("03") || sl[i].equals("05") || sl[i].equals("07") || sl[i].equals("11")) {
                hezs++;
            }
        }
        for (int i = 0; i < jh.length; i++) {
            if (jh[i].indexOf(String.valueOf(hezs)) == 0) {
                return true;
            }
        }
        return false;
    }

    /*
     * String[] sl  ：要校验的号码
     * int[] hez ：选中的重号个数
     * String upNum:上期的号码
    * */
    public static boolean ChongHaoVerify(String[] sl, Integer[] jh, String upNum) {
        int hezs = 0; //计算重号的个数
        for (int i = 0; i < sl.length; i++) {
            if (upNum.indexOf(sl[i]) >= 0) {
                hezs++;
            }
        }
        for (int i = 0; i < jh.length; i++) {
            if (jh[i] == hezs) {
                return true;
            }
        }
        return false;
    }

    /*
     * String[] sl  ：要校验的号码
     * String[] hez ：选中的大号小号比个数
    * */
    public static boolean DaHaoVerify(String[] sl, String[] jh) {
        int hezs = 0; //计算质数个数
        for (int i = 0; i < sl.length; i++) {
            if (sl[i].equals("06") || sl[i].equals("07") || sl[i].equals("08") || sl[i].equals("09") || sl[i].equals("10") || sl[i].equals("11")) {
                hezs++;
            }
        }
        for (int i = 0; i < jh.length; i++) {
            if (jh[i].indexOf(String.valueOf(hezs)) == 0) {
                return true;
            }
        }
        return false;
    }

    /*
       * String[] sl  ：要校验的号码
       * int[] lh ：选中的连号类型
      * */
    public static boolean LianHaoVerify(String[] sl, String[] lh) {
        //一个两连
        int two1Lian = 0; //第一个连数
        boolean two1Lianwz = false; //第一个连接是否已经中断
        int two2Lian = 0; //第二个连数
        for (int j = 0; j < sl.length; j++) {
            if (j == 0) {
                continue;
            }
            if ((Integer.valueOf(sl[j]) - Integer.valueOf(sl[j - 1]) > 1) && two1Lian > 0) {
                two1Lianwz = true;
                continue;
            }
            if ((Integer.valueOf(sl[j]) - Integer.valueOf(sl[j - 1]) == 1) && two1Lianwz == false) {
                if (two1Lian == 0) //第一个连数
                {
                    two1Lian = 2;
                } else {
                    two1Lian++;
                }
            } else if ((Integer.valueOf(sl[j]) - Integer.valueOf(sl[j - 1]) == 1) && two1Lianwz == true) //开始第二连
            {
                if (two2Lian == 0) //第一个连数
                {
                    two2Lian = 2;
                } else {
                    two2Lian++;
                }
            }
        }

        if (two1Lian == 0 && two2Lian == 0) //查看完全不连是否选中else
        {
            /*int positon = Arrays.binarySearch(lh, "完全不连");
            if (positon < 0) {
                return false;
            }*/
            return binarySearch(lh, "完全不连");
        } else if (two1Lian == 2 && two2Lian == 0) //只有一个2连
        {
            /*int positon = Arrays.binarySearch(lh, "一个两连");
            if (positon < 0) {
                return false;
            }*/
            return binarySearch(lh, "一个两连");
        } else if (two1Lian == 2 && two2Lian == 2) //2个2连
        {
            /*int positon = Arrays.binarySearch(lh, "两个两连");
            if (positon < 0) {
                return false;
            }*/
            return binarySearch(lh, "两个两连");
        } else if (two1Lian == 3 && two2Lian == 0) //只有一个3连
        {
            /*int positon = Arrays.binarySearch(lh, "只有三连");
            if (positon < 0) {
                return false;
            }*/
            return binarySearch(lh, "只有三连");
        } else if ((two1Lian == 2 && two2Lian == 3) || (two2Lian == 2 && two1Lian == 3)) //两连三连各一
        {
            /*int positon = Arrays.binarySearch(lh, "两连三连各一个");
            if (positon < 0) {
                return false;
            }*/
            return binarySearch(lh, "两连三连各一个");
        } else if (two1Lian == 4) //4连
        {
            /*int positon = Arrays.binarySearch(lh, "只有四连");
            if (positon < 0) {
                return false;
            }*/
            return binarySearch(lh, "只有四连");
        } else if (two1Lian == 5) //5连
        {
            /*int positon = Arrays.binarySearch(lh, "只有五连");
            if (positon < 0) {
                return false;
            }*/
            return binarySearch(lh, "只有五连");
        }
        return true;
    }

    private static boolean binarySearch(String[] sl, String text){
        for(String str : sl){
            if(str.trim().equals(text)){
                return true;
            }
        }
        return false;
    }


    /*
     * String[] sl  ：要校验的号码
     * String[] hez ：选中的012路比
    * */
    public static boolean Ratio012Verify(String[] sl, String[] jh) {
        int h_0 = 0; //0路
        int h_1 = 0; //1路
        int h_2 = 0; //2路
        for (int i = 0; i < sl.length; i++) {
            if (sl[i].equals("03") || sl[i].equals("06") || sl[i].equals("09")) {
                h_0++;
            } else if (sl[i].equals("01") || sl[i].equals("04") || sl[i].equals("07") || sl[i].equals("10")) {
                h_1++;
            } else {
                h_2++;
            }
        }
        String bl = h_0 + ":" + h_1 + ":" + h_2;
        for (int i = 0; i < jh.length; i++) {
            if (jh[i].equals(bl)) {
                return true;
            }
        }
        return false;
    }

    /*
   * String[] sl  ：要校验的号码
   * int[] hez ：选中的平均值
  * */
    public static boolean AvgValueVerify(String[] sl, Integer[] hez) {
        int hezs = 0; //计算和值
        for (int i = 0; i < sl.length; i++) {
            hezs += Integer.valueOf(sl[i]);
        }
        //#####
        //int hzw = Integer.valueOf(hezs / 5);
        int hzw = Math.round((float)hezs/5);
        for (int i = 0; i < hez.length; i++) {
            if (hez[i] == hzw) {
                return true;
            }
        }
        return false;
    }

    public static String jisuanLinhao(String winNum){

        String[] array = winNum.split(" ");
        List<String> linhaoArray = new ArrayList<>();
        for(String str : array){
            int value = Integer.parseInt(str);
            if(value == 1){
                if(!linhaoArray.contains("02")){
                    linhaoArray.add("02");
                }
            }else if(value == 11){
                if(!linhaoArray.contains("10")){
                    linhaoArray.add("10");
                }
            }else if(value == 9){
                if(!linhaoArray.contains("10")){
                    linhaoArray.add("10");
                }
                if(!linhaoArray.contains("08")){
                    linhaoArray.add("08");
                }
            }else if(value == 10){
                if(!linhaoArray.contains("11")){
                    linhaoArray.add("11");
                }
                if(!linhaoArray.contains("09")){
                    linhaoArray.add("09");
                }
            }else{
                if(!linhaoArray.contains("0"+(value-1))){
                    linhaoArray.add("0"+(value-1));
                }
                if(!linhaoArray.contains("0"+(value+1))){
                    linhaoArray.add("0"+(value+1));
                }
            }
        }
        StringBuilder sb = new StringBuilder("");
        for(int i=0; i < linhaoArray.size(); i++){
            if(i == linhaoArray.size()-1){
                sb.append(linhaoArray.get(i));
            }else{
                sb.append(linhaoArray.get(i)).append(" ");
            }
        }
        String linhao = sb.toString();
        return linhao;
    }

    /*
        * String[] sl  ：要校验的号码
        * int[] linCount ：选中的邻号个数
        * String upNumLin:根据上期号码算出来的所有邻号
       * */
    public static boolean LinHaoCountVerify(String[] sl, Integer[] linCount, String upNumLin) {
        /* var lhs = new StringBuilder(""); //拼接好的邻号
        foreach (string s in lhsl)
        {
            lhs.Append((Convert.ToInt32(s) + 1).ToString().PadLeft(2, '0')).Append(" ");
            lhs.Append((Convert.ToInt32(s) - 1).ToString().PadLeft(2, '0')).Append(" ");
        }*/
        int lihaoCount = 0;
        for (int i = 0; i < sl.length; i++) {
            if (upNumLin.indexOf(sl[i]) >= 0) {
                lihaoCount++;
            }
        }
        for (int i = 0; i < linCount.length; i++) {
            if (linCount[i] == lihaoCount) {
                return true;
            }
        }
        return false;
    }

/*    public static boolean YichuhaoPaichu(String num, String qishu, String cishu){
        KaiJiang_11x5Dao K
    }*/

    /**
     * 已出号排除（例如：设置最近100期开出2次，则最近100期内开出2期以上的号码会被排除。）
     * @param num 号码
     * @param cishu 出号次数
     * @param kjLists 开奖号码列表
     * @return
     */
    public static boolean yichuhaoPaichu(String num, String cishu, List<KaiJiang_11x5> kjLists){
        try{
            int count = 0;
            for(KaiJiang_11x5 kaiJiang_11x5: kjLists){
                String sortNum = kaiJiang_11x5.getLuckyNumberSort();
                if(sortNum.equals(num)){
                    count+=1;
                    if(count >= Integer.parseInt(cishu)){
                        return false;
                    }
                }
            }
        }catch (Exception e){
        }
        return true;
    }


}

