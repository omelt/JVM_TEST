package com.company;

import java.lang.invoke.*;

import static java.lang.invoke.MethodHandles.lookup;

public class InvokeDynamicTest {
    public static void main(String[] args) throws Throwable {
        INDY_BootstrapMethod().invokeExact("testlc");
    }

    public static void testMethod(String s){
        System.out.println("hello String:" +s);
    }

    public static CallSite BootstrapMethod(MethodHandles.Lookup lookup
                                           , String name, MethodType mt) throws NoSuchMethodException, IllegalAccessException {
        return new ConstantCallSite(lookup.findStatic(InvokeDynamicTest.class,name,mt));
    }

    public static MethodType MT_BootstrapMethod(){
        return MethodType.fromMethodDescriptorString(
                "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;",
                null
        );
    }

    private static MethodHandle MH_BootstrapMethod() throws Throwable{
        return lookup().findStatic(InvokeDynamicTest.class,
                "BootstrapMethod",MT_BootstrapMethod());
    }

    private static MethodHandle INDY_BootstrapMethod() throws Throwable{
        CallSite cs=(CallSite)MH_BootstrapMethod().invokeWithArguments(lookup(),"testMethod",
                MethodType.fromMethodDescriptorString("(Ljava/lang/String;)V",null));
        return cs.dynamicInvoker();

    }
}
// 代码每一步作用还是比较好理解的
// 使用INDY工具查看查看字节码
// 可以看见原来原来的调用指令已经替换为invokedynamic
// BootstrapMethod() 是INDY中产生 它的主要逻辑就是调用
// MethodHandle&Lookup的findStatic方法,产生testMethod()方法的
// MethodHandle,然后用它创建一个ConstantCallSite对象.最后返回给
// invokeDynamic指令实现对testMethod()的方法的调用



