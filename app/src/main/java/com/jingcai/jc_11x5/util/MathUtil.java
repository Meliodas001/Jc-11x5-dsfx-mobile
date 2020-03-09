package com.jingcai.jc_11x5.util;

/**
 * Created by yangsen on 2017/5/2.
 */

public class MathUtil {
    public static boolean isZhishu(int n){
        if(n==1){
            return true;
        }
        int sum;      //计算存放的变量
        int t=0;       //累计变量t，用于计算整除次数的累计变量，用于判断质数和合数的过度变量
        for(int i=n-1;i>=1;i--) {    //  i 是规划 和n值相除的范围数 就是 1—28的范围，
            // 假如 n=33，那么这里就是1—32范围
            if(t>1){
                return false;
            }
            sum = n % i;            //求余数值
            if(sum == 0)       //若余数=0的，话，执行 t 累加
                t++;           //  条件满足，循环累加  （任何数除于1，都成立，所以t必须至少=1的。）
        }
        if (t>1){     //若t 累加 超过了1，证明不是质数，这是判断质数 合数的方法
            return false;
        }
        return true;
    }
}
