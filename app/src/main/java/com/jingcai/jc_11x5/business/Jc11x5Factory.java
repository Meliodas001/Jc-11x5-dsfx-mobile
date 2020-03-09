package com.jingcai.jc_11x5.business;

import com.jingcai.jc_11x5.business.impl.Jc11x5Thread;

/**
 * Created by yangsen on 2016/12/10.
 */
public class Jc11x5Factory {

    private static Jc11x5Interface mJc11x5Interface;

    public static Jc11x5Interface getInstance() {
        if (mJc11x5Interface == null) {
            mJc11x5Interface = (Jc11x5Interface) new Jc11x5Thread();
        }
        return mJc11x5Interface;
    }

}
