package com.jingcai.jc_11x5.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangsen on 2018/1/19.
 */

public class PlanFabu {

    public List<HaoMa> getHaoMaList(){
        List<HaoMa> list = new ArrayList<>();
        HaoMa haoMa1 = new HaoMa("01", false);
        HaoMa haoMa2 = new HaoMa("02", false);
        HaoMa haoMa3 = new HaoMa("03", false);
        HaoMa haoMa4 = new HaoMa("04", false);
        HaoMa haoMa5 = new HaoMa("05", false);
        HaoMa haoMa6 = new HaoMa("06", false);
        HaoMa haoMa7 = new HaoMa("07", false);
        HaoMa haoMa8 = new HaoMa("08", false);
        HaoMa haoMa9 = new HaoMa("09", false);
        HaoMa haoMa10 = new HaoMa("10", false);
        HaoMa haoMa11 = new HaoMa("11", false);
        list.add(haoMa1);
        list.add(haoMa2);
        list.add(haoMa3);
        list.add(haoMa4);
        list.add(haoMa5);
        list.add(haoMa6);
        list.add(haoMa7);
        list.add(haoMa8);
        list.add(haoMa9);
        list.add(haoMa10);
        list.add(haoMa11);
        return list;
    }

    public class HaoMa{
        private String haoma;
        private boolean isChecked;

        public HaoMa() {
        }

        public HaoMa(String haoma, boolean isChecked) {
            this.haoma = haoma;
            this.isChecked = isChecked;
        }

        public String getHaoma() {
            return haoma;
        }

        public void setHaoma(String haoma) {
            this.haoma = haoma;
        }

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }
    }
}
