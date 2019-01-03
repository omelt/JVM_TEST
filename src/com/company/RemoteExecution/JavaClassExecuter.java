package com.company.RemoteExecution;




import java.lang.reflect.Method;

/**
 * JavaClass 执行工具
 *
 * 提供外部调用的入口 调用前面的类完成加载工作
 * 只有一个execute方法,用输入符合Class文件格式的byte[]数组替换
 * lang.System的符号引用后,用HotSwapClassLoader加载生成一个class
 * 对象,每次执行execute方法都会生成一个新的类加载器实例,实现一个类可以重复加载
 * 然后反射调用main方法,输出结果作为返回 ,而异常打印到HackSystem.out中
 *
 * @author lc
 */
public class JavaClassExecuter {

    /**
     * 执行外部传过去的代表一个JAVA内的byte数组
     * 将输入类的byte数组中代表lang.System的CONSTANT_Utf8_info常量
     * 改为劫持后的HackSystem类
     * 执行目标的main方法 结果为该类System.out/err输出信息
     *
     * @param classByte 代表一个Java类的byte数组
     * @return 执行结果
     */
    public static String execute(byte[] classByte){
        HackSystem.clearBuffer();
        ClassModifier cm=new ClassModifier(classByte);
        byte[] modiBytes=cm.modifyUTF8Contant(
                "java/lang/System",
                "com/company/RemoteExecution/HotSwapClassLoader");
        HotSwapClassLoader loader=new HotSwapClassLoader();
        Class tar = loader.loadByte(modiBytes);
        try {
            Method method=tar.getMethod("main",new Class[]{String[].class});
            method.invoke(null,new String[]{null});
        } catch (Exception e) {
            e.printStackTrace(HackSystem.out);
        }
        return HackSystem.getBufferString();
    }
}
