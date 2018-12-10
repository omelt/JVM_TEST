package com.company;

/**
 * VM args : -verbose:gc -Xms20M -Xms20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 */
public class TestAllocation {

    private static final int _1MB = 1024*1024;

    public static void main(String[] args) {
        byte[] allocation1,allocation2,allocation3,allocation4;
        allocation1=new byte[2 * _1MB];
        allocation2=new byte[2 * _1MB];
        allocation3=new byte[2 * _1MB];
        allocation4=new byte[4 * _1MB]; //出现minor GC
        // 新生代空间不够 使用了老年代
    }
}