package com.company;

/**
 * 方法动态分派演示
 * @author lc
 */
public class DynamicDispatch {

    static abstract class Human{
        protected abstract void sayHello();
    }

    static class Man extends Human {
        @Override
        protected void sayHello() {
            System.out.println("man say hello");
        }
    }
    static class Woman extends Human{
        @Override
        protected void sayHello() {
            System.out.println("woman say-hello");
        }
    }

    public static void main(String[] args){
        Human man=new Man();
        Human woman = new Woman();
        man.sayHello();
        woman.sayHello();
        man = new Woman();
        man.sayHello();
    }
    /**
     * javap -verbose 查看下字节码
     *
     * 16,20 把创建的对象压入栈中 他们是将要执行的方法的接收者
     * 17,21 方法调用指令 invokevirtual
     * 虽然看上去完全一样,但是执行的目标方法不同.
     * 这和invokevirtual指令本身多态查找有关:
     *  1.找到操作数栈项的第一个元素指向的对象的实际类型(记作C)
     *  2.类型C中找到于常量中 的描述符和简单名称都相符的方法,则进行访问权限
     *  校验,通过则返回方法的直接引用,不通过这抛出AbstractMethodError
     *  3.否则,按照继承关系从下往上依次对C的各个父类进行第2步的搜索和验证过程
     *  4.如果始终没有找到合适的方法,这抛出AbstractMethod
     * 在invokevirtual第一步,确定的实际类型把符号引用解析到不同直接引用上
     * 这也是重写的本质,运行时根据实际类型确定方法版本就叫动态分配
     *
     */
}
