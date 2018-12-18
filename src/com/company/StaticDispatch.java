package com.company;

/**
 * 方法静态分派演示
 */
public class StaticDispatch {
    static abstract class Human{

    }
    static class Man extends Human{

    }

    static class Woman extends Human{

    }

    public void sayHello(Human guy){
        System.out.println("hello,Human!");
    }
    public void sayHello(Man guy){
        System.out.println("hello,Man!");
    }
    public void sayHello(Woman guy){
        System.out.println("hello,Woman!");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman=new Woman();

        StaticDispatch sd=new StaticDispatch();

        sd.sayHello(man);
        sd.sayHello(woman);
        //
        sd.sayHello((Man)man);
        sd.sayHello((Woman) woman);
    }
}
/**
 * 答案当然简单 但主要是为什么
 * Human man = new Man();
 * 上面 Human 称为变量的静态类型,或者叫外观类型
 * 后面的 Man 称为实际类型.这两个类型都可以在程序中发生一些
 * 变化,不同的是,静态类型的变化仅仅在使用发生,变量本身的静态类型不会变
 * 并且最终的静态类型在编译期可知,而实际类型的变化只有运行期才可确定
 * 编译时并不知道实际类型是啥
 *
 * 所以上面的代码编译器重载时是通过静态类型而不是实际类型,
 */
