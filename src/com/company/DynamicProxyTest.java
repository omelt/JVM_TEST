package com.company;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyTest {
    interface IHello {
        void sayHello();
    }

    static class Hello implements IHello{
        @Override
        public void sayHello() {
            System.out.println("Hello World!");
        }
    }

    static class DynamicProxy implements InvocationHandler{

        Object originObj;

        Object bind(Object originObj){
            this.originObj=originObj;
            return Proxy.newProxyInstance(
                    originObj.getClass().getClassLoader(),
                    originObj.getClass().getInterfaces(),
                    this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("welcome");
            return method.invoke(originObj,args);
        }


    }
    public static void main(String[] args) {
        System.getProperties().put(
                "sun.misc.ProxyGenerator.saveGeneratedFiles",
                "true");
        //加入后再运行,会产生一个名为"$Proxy0.class"的代理类Class文件
        //应该是动态代理的中间类
        IHello hello=(IHello) new DynamicProxy().bind(new Hello());
        hello.sayHello();
    }
}
