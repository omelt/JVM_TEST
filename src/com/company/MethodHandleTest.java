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
    }
}
