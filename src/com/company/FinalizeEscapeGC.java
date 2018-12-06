package com.company;

public class FinalizeEscapeGC {
    public static FinalizeEscapeGC SAVE_HOOK=null;

    public void isAlive(){
        System.out.println("yes,i am still alive:)");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("do finalize");
        FinalizeEscapeGC.SAVE_HOOK=this;
    }

    public static void main(String[] args)throws Throwable{
        SAVE_HOOK = new FinalizeEscapeGC();

        //第一次救自己
        SAVE_HOOK=null;
        System.gc();
        //确保finalize执行
        Thread.sleep(500);
        if(SAVE_HOOK!=null){
            SAVE_HOOK.isAlive();
        }else{
            System.out.println("fail");
        }

        //第二次救自己
        SAVE_HOOK=null;
        System.gc();
        //确保finalize执行
        Thread.sleep(500);
        if(SAVE_HOOK!=null){
            SAVE_HOOK.isAlive();
        }else{
            System.out.println("fail");
        }
    }
}
