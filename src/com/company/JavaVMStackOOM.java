package com.company;

/**
 * VM Args: -Xss228k (设置稍微大些)
 * @author lc
 */

//千万不要跑  没测出来  会卡死
    //正常应该是 -Xss2M然后 出现 OOM错误  可惜了
    // OS: openSUSE 20181126 Kernel: x86_64 Linux 4.19.4-1-default
public class JavaVMStackOOM {

    private void dontStop(){
        while (true){
//            System.out.println(Thread.currentThread().getName()+"dontStop()");
        }
    }

    public void stackLeakByThread(){
        while (true){
            new Thread(()->dontStop()).start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM javaVMStackOOM=new JavaVMStackOOM();
        javaVMStackOOM.stackLeakByThread();
    }
}
