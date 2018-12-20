package com.company;

import java.io.Serializable;

public class OverloadLevelTest {

    public static void sayHello(Object arg){
        System.out.println("hello Object");
    }

//    public static void sayHello(int arg){
//        System.out.println("Hello int");
//    }

//    public static void sayHello(long arg){
//        System.out.println("hello long");
//    }

//    public static void sayHello(Character arg){
//        System.out.println("hello Character");
//    }
//    public static void sayHello(char arg){
//        System.out.println("hello char");
//    }
    public static void sayHello(char...arg){
        System.out.println("hello char...");
    }
//    public static void sayHello(Serializable arg){
//        System.out.println("hello Serializable");
//    }

    public static void main(String[] args) {
        sayHello('a');
    }

    /**
     * 很好理解 输出char
     * 如果注释掉sayHello(char arg) 那么输出int
     * (不会是char...或其他) 选择一个最合适版本
     * 注视掉sayHello(int arg) 输出 long
     * char ->int->long->float->double
     * (不会匹配到byte和short,这是不安全转换)
     * 注释掉(long arg) 则输出变为Character
     * 在这里进行了一次自动装箱 'a'->lang.Character
     * 如果注释掉(Character arg)那么输出Serializable
     * (Serializable是Character实现的一个接口,虽然找不到可转
     * 换的类型 和 可装箱的类 但是找到了装箱类的接口类型)
     * 再注释掉 Serializable输出 object
     * (这是装箱类变成父类了 越上层的父类 优先级越低)
     * 再注释就输出char ...
     */

    /**
     * 静态分派目标的过程也是java实现方法重载的本质
     * 当然实际要避免实际出现这样的重载情况
     */
}
