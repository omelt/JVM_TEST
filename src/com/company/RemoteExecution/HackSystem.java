package com.company.RemoteExecution;

import sun.nio.ch.Interruptible;
import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;
import sun.reflect.annotation.AnnotationType;
import sun.security.util.SecurityConstants;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Executable;
import java.nio.channels.Channel;
import java.nio.channels.spi.SelectorProvider;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Map;
import java.util.Properties;
import java.util.PropertyPermission;

/**
 * 为JavaClass劫持lang.System提供支持
 * 除了out和err外,其余的都直接转发给System处理
 *
 * 替代lang.System的HackSystem ,方法很多,但是其实出来把out和err改成
 * ByteArrayOutputStream作为打印同一个PrintStream对象,以及添加读取
 * ,清除ByteArrayOutputStream的两个方法外就没啥了,其余方法参数全部调用原来的
 *
 * @author lc
 */
public class HackSystem {

    public final static InputStream in = System.in;

    private static ByteArrayOutputStream buffer = new ByteArrayOutputStream();

    public final static PrintStream out = new PrintStream(buffer);

    public final static PrintStream err = out;


//  方法来自lang.System  都是调用 System对应方法
// ============================================

    public static void setIn(InputStream in) {
       System.setIn(in);
    }

    public static void setOut(PrintStream out) {
        System.setOut(out);
    }

    public static void setErr(PrintStream err) {
        System.setErr(err);
    }

    public static Console console() {
       return System.console();
    }

    public static Channel inheritedChannel() throws IOException {
        return System.inheritedChannel();
    }


    public static long nanoTime(){
        return  System.nanoTime();
    }

    public static Properties getProperties() {
        return System.getProperties();
    }


    public static String lineSeparator() {
        return System.lineSeparator();
    }

    public static void setProperties(Properties props) {
        System.setProperties(props);
    }

    public static String getProperty(String key) {
       return System.getProperty(key);
    }

    public static String getProperty(String key, String def) {
       return System.getProperty(key,def);
    }

    public static String setProperty(String key, String value) {
       return  System.setProperty(key,value);
    }


    public static String clearProperty(String key) {
        return System.clearProperty(key);
    }


    public static String getenv(String name) {
        return System.getenv(name);
    }

    public static java.util.Map<String,String> getenv() {
        return System.getenv();
    }

    public static void exit(int status) {
        System.exit(status);
    }

    public static void gc() {
        System.gc();
    }

    public static void runFinalization() {
        System.runFinalization();
    }

    public static void runFinalizersOnExit(boolean value) {

        System.runFinalizersOnExit(value);
    }

    public static void load(String filename) {
        System.load(filename);
    }


    public static void loadLibrary(String libname) {
        System.loadLibrary(libname);
    }


    public static  String mapLibraryName(String libname){
        return System.mapLibraryName(libname);
    }

// ============================================
// ============================================

    public static String getBufferString(){
        return buffer.toString();
    }

    public static void clearBuffer(){
        buffer.reset();
    }

    public static void setSecurityManager(final SecurityManager s){
        System.setSecurityManager(s);
    }

    public static SecurityManager getSecurityManager(){
        return System.getSecurityManager();
    }

    public static long currentTimeMillis(){
        return System.currentTimeMillis();
    }

    public static void arraycopy(Object src,int srcPos,Object dest,int destPos,int length){
        System.arraycopy(src,srcPos,dest,destPos,length);
    }

    public static int identityHashCode(Object x){
        return System.identityHashCode(x);
    }

}
