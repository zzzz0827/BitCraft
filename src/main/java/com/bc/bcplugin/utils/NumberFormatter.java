package com.bc.bcplugin.utils;

import java.text.DecimalFormat;

public class NumberFormatter {

    public static Object money(int money) {
        DecimalFormat formatter = new DecimalFormat("###,###.#");
        return formatter.format(money) + "원";
    }

    public static Object money(String money) {
        DecimalFormat formatter = new DecimalFormat("###,###.#");
        return formatter.format(Double.parseDouble(money)) + "원";
    }

    public static Object money(Object money) {
        DecimalFormat formatter = new DecimalFormat("###,###.#");
        return formatter.format(Double.parseDouble((String) money)) + "원";
    }

    public static Object number(int money) {
        DecimalFormat formatter = new DecimalFormat("###,###");
        return formatter.format(money) + "개";
    }

    public static Object number(String money) {
        DecimalFormat formatter = new DecimalFormat("###,###");
        return formatter.format(Double.parseDouble(money)) + "개";
    }

    public static Object number(Object money) {
        DecimalFormat formatter = new DecimalFormat("###,###");
        return formatter.format(Double.parseDouble((String) money)) + "개";
    }
}
