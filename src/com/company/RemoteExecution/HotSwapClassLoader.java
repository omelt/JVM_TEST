package com.company.RemoteExecution;


/**
 * 为了多次加载执行类而加入的加载器<br>
 * 把defineClass方法开放出来,只有外部显式调用
 * 由虚拟机调用时,仍然按照原有的双亲委派规则使用loadClass
 * 方法进行类加载
 *
 * @author lc
 */
public class HotSwapClassLoader extends ClassLoader {

    public HotSwapClassLoader() {
        super(HotSwapClassLoader.class.getClassLoader());
    }

    public Class loadByte(byte[] classByte){
        return defineClass(null,classByte,0,classByte.length);
    }
}
/*
所做的事情仅仅是公开父类中的protect方法defineClass
使用这个方法把提交执行的Java类的byte[]数组转变为Class对象
HotSwapClassLoader中并没有重写loadClass()或findClass()因此如果
不算外部手工调用loadByte方法的话,这个类加载器的查找范围与他的父类加载器是
完全一样的,如果不算外部手工调用loadByte()方法的话,这个类加载器的类查找范围与
它的父类加载器是完全一致的在被虚拟机调用时,他会按照双亲委派模型给父类加载.
构造函数中指定为加载HotSwapClassLoader类的类加载器作为父类加载器,这一步是实现
提交的执行代码可以访问服务端引用类库的关键
 */
