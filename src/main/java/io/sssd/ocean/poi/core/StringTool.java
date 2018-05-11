package io.sssd.ocean.poi.core;

public class StringTool {

    public static boolean isEmpty(String s) {
        return "".equals(s) || s == null ? true : false;
    }

    public static boolean isNotEmpty(String s) {
        return "".equals(s) || s == null ? false : true;
    }


    public static void main(String[] args) {

        String ss = "a";

        System.out.println(isEmpty(ss));


        System.out.println(isNotEmpty(ss));


    }

}
