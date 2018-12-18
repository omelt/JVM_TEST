package com.company;

/**
 * 方法静态解析展示
 * @author lc
 */
public class StaticResolution {

    public static void sayHello(){
        System.out.println("hello world");
    }

    public static void main(String[] args) {
        StaticResolution.sayHello();
    }
}
//使用javap -verbose 可以看到字节码