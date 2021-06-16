package com.bc.bcplugin.utils;

import java.text.DecimalFormat;

public class MoneyFormatter {

    public static String format(int money) {
        DecimalFormat formatter = new DecimalFormat("###,###");
        String resultMoney = formatter.format(money) + "원";
        return resultMoney;
    }

    public static String format(String money) {
        DecimalFormat formatter = new DecimalFormat("###,###");
        String resultMoney = formatter.format(money) + "원";
        return resultMoney;
    }

    public static String format(Object money) {
        DecimalFormat formatter = new DecimalFormat("###,###");
        String resultMoney = formatter.format(money) + "원";
        return resultMoney;
    }
}
