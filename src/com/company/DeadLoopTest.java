package com.company;

class DeadLoopClass {
    static {
        if (true) { //不加这个编译出错 Initializer does not complete normally  java: 初始化程序必须能够正常完成
            System.out.println(Thread.currentThread() + "init DeadLoopClass");
            while (true) {
            }
        }
    }

}
public class DeadLoopTest {


    public static void main(String[] args) {
        Runnable script = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + " start");
                DeadLoopClass deadLoopClass = new DeadLoopClass();
                System.out.println(Thread.currentThread() + " run over");
            }
        };
        Thread thread1 = new Thread(script);
        Thread thread2 = new Thread(script);
        thread1.start();
        thread2.start();
        //<client>造成多个线程阻塞
    }
}
