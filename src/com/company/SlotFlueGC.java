package com.company;


/**
 * VM args:-verbose:gc
 * @author lc
 */
public class SlotFlueGC {
    public static void main(String[] args) {
//        byte[] placeholder = new byte[64*1024*1024];
//        System.gc();
//        不会回收 placeholder 他还在作用域上 jvm自然不会回收

        {
            byte[] placeholder = new byte[64*1024*1024];

        }
//        System.gc();
//        仍然不会回收
        int a=0;
        System.gc();
//        进行回收

    }
    /**
     * placehold能回收的根本原因是局部变量表的slot是否还存有关于placeholder数组
     * 对象的引用.第一次修改中,代码虽然已经离开了placehold作用域但是没有任何对局部
     * 变量表的读写操作,placehold的slot没有复用,所以作为GC roots一部分保持着的局部
     * 变量表仍然保持着关联.
     *
     * 绝大多数情况下,这种关联没有及时的中断,绝大多数情况下都很轻微,但是如果一个实际方法
     * 后面代码有一堆耗时很长的操作,前面却又定义了大量占用内存的对象,但却不再使用
     * --这时手动赋予 null 并不是一个没用的操作--帮助进行内存回收
     * java链表的代码(大概)也用到这一点
     */
}
