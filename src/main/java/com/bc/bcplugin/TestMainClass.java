package com.bc.bcplugin;

public class TestMainClass {
    public static void main(String[] args) {
        try {
            System.out.println((char[]) null);
        }catch (NullPointerException e) {
        }
    }
}
