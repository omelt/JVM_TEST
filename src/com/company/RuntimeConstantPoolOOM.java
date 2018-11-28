package com.company;

import java.util.ArrayList;
import java.util.List;

/**
 * VM args:-XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=20m
 * @author lc
 */


public class RuntimeConstantPoolOOM {
//    static String  base = "string";
    public static void main(String[] args) {
        //使用List保持常量池的引用 避免GC
//        List<String> list=new ArrayList<>();
//
//
//        while (true){
//            String str = base + base;
//            base = str;
//            list.add(str.intern());
//        }
        // 上面两个设置好像没用  吃到大概1G多内存就报java.lang.OutOfMemoryError: Java heap space
        //预期是想报个OOM：Metaspace


        //==================

        String str1=new StringBuilder("中文").append("哇").toString();
        System.out.println(str1.intern()==str1); //true

        String str2=new StringBuilder("ja").append("va").toString();
        //常量池中已经有了 java 这个字符串
        System.out.println(str2.intern()==str2); //false


    }

}
