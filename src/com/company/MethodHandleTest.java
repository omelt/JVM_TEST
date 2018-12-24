package com.company;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import static java.lang.invoke.MethodHandles.lookup;

/**
 * Method Handle 基础用法演示
 * @author lc
 */
public class MethodHandleTest {
    static class ClassA{
        public void println(String s){
            System.out.println(s+" my");
        }
    }

    public static void main(String[] args) throws Throwable {
        Object obj = System.currentTimeMillis() % 2 == 0?System.out:new ClassA();

        getPrintlnMH(obj).invokeExact("testlc");
    }

    public static MethodHandle getPrintlnMH(Object object) throws Throwable{

        /*
        MethodType : 代表"方法类型",包含了方法的返回值(methodType()的第一个参数)和
        具体参数(methodType()第二个参数)
         */
        MethodType mt = MethodType.methodType(void.class,String.class);
        /*
        方法来自于MethodHandles.lookup,作用是在给定类中查找符合给定的方法
        的名称,类型.并且符合调用权限的方法句柄.
        =============================
        因为这里调用的是一个虚方法,按照java语言的规则,方法第一个参数是隐式,代表
        该方法的接收者,也是this指向的对象,这个参数以前是放在参数列表中进行传递的
        而现在提供了bindTo完成这件事情
         */
        return lookup().findVirtual(object.getClass(),"println",mt)
                .bindTo(object);

//        模拟了invokevirtual指令的执行过程
//        仅仅java角度上反射和MethodHandle(方法句柄)的使用效果很相似
//        本质上,他们都是在模拟方法调用,但是反射是模拟java代码上的调用.
//        而MethodHandle是在模拟字节码层面的调用.lookup中包括三个方法
//        findStatic(),findVirtual(),findSpecial()对应
//        invokestatic;invokevirtual&invokeinterface;invokespecial
//        这几条指令的校验行为.
//        反射包含的信息多,包含java端的各种表示方式,是重量级操作,而另一个是轻量级的
//        MethodHandle是字节码方法指令的模拟,所以虚拟机会对其作各种优化.
    }
}
