package com.company;

/**
 * 被动使用类字段演示一:
 * 通过子类引用父类的静态字段,不会导致子类初始化
 */
class SuperClass{
    static {
        System.out.println("SuperClass init!");
    }

    public static int value = 123;

    public static final String hello="hello";
}

class SubClass extends SuperClass {
    static {
        System.out.println("SubClass init!");
    }
}


public class NotInitialization {
    /**
     *非主动类字段演示
     */
//    public static void main(String[] args) {
//        System.out.println(SubClass.value);
//    }
//不会输出  SubClass init!
//由于静态字段 只有<直接>定义这个字段的类
//才会被初始化 因此子类引用父类的字段
//只会出现父类初始化
//在Sun HotSpot虚拟机可通过
//-XX:+TraceClassLoading这可以观察到

    /**
     * 被动使用类字段演示二:
     * 通过数组定义引用类
     * 不会出发此类的初始化
     */
//    public static void main(String[] args) {
//        SubClass[] subClasses=new SubClass[10];
//    }

    /**
     * 被动使用类字段演示三:
     * 常量在编译阶段会存入调用类的常量池中,本质上并没有直接引用到定义常量的类,因此不会触发常量的类的初始化.
     */
    public static void main(String[] args) {
        System.out.println(SuperClass.hello);
    }
    //没有初始化虽然引用了SuperClass的值
    //但是编译阶段 已经通过常量传播传递优化
    //"hello"被放在了NotInitialization
    //类的常量池,所以现在对SuperClass.hello
    //都被转化成为类对自身常量池的引用
    //两个类编译后啥关系事没有
    //接口和类基本相似,但是intetface中不能
    //定义static{}所以不好实验
}

