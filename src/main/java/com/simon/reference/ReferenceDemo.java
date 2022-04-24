package com.simon.reference;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReferenceDemo {
    public static void main(String[] args) {
        test02();
    }

    // true,每个类的class对象在jvm中只有一个
    public static void test01() {
        try {
            Class<SmallPineapple> smallPineappleClass = SmallPineapple.class;
            SmallPineapple smallPineapple = new SmallPineapple();
            Class<? extends SmallPineapple> aClass = smallPineapple.getClass();
            Class<?> aClass1 = Class.forName("com.simon.reference.SmallPineapple");
            System.out.println(smallPineappleClass == aClass);
            System.out.println(aClass1 == aClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void test02() {
        try {
            Class<SmallPineapple> smallPineappleClass = SmallPineapple.class;
            Constructor<SmallPineapple> constructor = smallPineappleClass.getConstructor();
            SmallPineapple smallPineapple = constructor.newInstance();
            // 调用无参构造，所有属性为默认值
            smallPineapple.getInfo(); // info : name is null age is null weight is 0.0

            // 调用有参构造
            Constructor<SmallPineapple> constructor1 = smallPineappleClass.getConstructor(String.class, Integer.class);
            SmallPineapple simon = constructor1.newInstance("simon", 18);
            simon.getInfo(); // info : name is simon age is 18 weight is 0.0

        } catch (Exception e) {
        }
    }
}
