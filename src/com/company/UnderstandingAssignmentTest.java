package com.company;


import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;

import static java.lang.invoke.MethodHandles.lookup;

/**
 * 理解掌握方法分派规则
 *
 */


public class UnderstandingAssignmentTest {
    class GrandFather{
        void thinking(){
            System.out.println("i am GrandFather ");
        }
    }

    class Father extends GrandFather{

        void thinking() {
            System.out.println("i am father");
        }
    }

    class Son extends Father{

        void thinking() {
            //想办法执行 GrandFather 的方法

            MethodType mt=MethodType.methodType(void.class);
            try {
                Field IMPL_LOOKUP = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
                IMPL_LOOKUP.setAccessible(true);
                MethodHandles.Lookup lkp = (MethodHandles.Lookup)IMPL_LOOKUP.get(null);

                MethodHandle h1 = lkp.findSpecial(GrandFather.class, "thinking", mt, GrandFather.class);
                h1.invoke(this);
            } catch (Throwable e) {
                e.printStackTrace();
            }


            //这样虽然还是和继承没关系 但是比其他方法还是要好
            //但是不知道为啥 没实现
        }
    }
    public static void main(String[] args) {
        new UnderstandingAssignmentTest().new Son().thinking();
    }
}
