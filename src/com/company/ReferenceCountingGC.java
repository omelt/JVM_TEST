package com.company;


/**
 * VM args: -XX:+PrintGCDetails
 * java没有 使用了引用计数法
 */
public class ReferenceCountingGC {

    public Object instance = null;

    private static final int _1MB = 1024*1024;

    //占用点内存
    public byte[] bigSize = new byte[2*_1MB];

    public static void main(String[] args) {
        ReferenceCountingGC objA=new ReferenceCountingGC();
        ReferenceCountingGC objB=new ReferenceCountingGC();
        objA.instance=objB;
        objB.instance=objA;

        objA=null;objB=null;

        //尝试回收
        //如果使用了 引用计数算法 则不会回收

        System.gc();
        System.out.println("=====");

        //gc回收了资源 实际使用的是可达性分析  更好处理类之间引用
    }
}
