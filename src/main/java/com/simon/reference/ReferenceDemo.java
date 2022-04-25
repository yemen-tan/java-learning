package com.simon.reference;


import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReferenceDemo {
    public static void main(String[] args) {
        test05();
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

    // 反射创建实例
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

            // 私有属性可以设置
            Constructor<SmallPineapple> constructor2 = smallPineappleClass.getConstructor(String.class, Integer.class, Double.class);
            SmallPineapple yemen = constructor2.newInstance("yemen", 18, 180.00);
            yemen.getInfo();// info : name is yemen age is 18 weight is 180.0

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // 分类中的protected属性，反射无法获取到
    public static void test03() {
        Class<SmallPineapple> smallPineappleClass = SmallPineapple.class;
        // 获取类中声明的属性，不包括父类中的
        Field[] declaredFields = smallPineappleClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField.getName());
        }

        // 获取public属性，包括父类中的，private的不能获取，父类中的protect属性无法获取，但是的的确确存在子类中
        Field[] fields = smallPineappleClass.getFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
    }

    // 获取方法上的注解
    public static void test04() {
        Class<SubSmallPineapple> subSmallPineappleClass = SubSmallPineapple.class;
        try {
            // getMethod 只能获取到public的方法，父类中的protect无法获取
            Method getInfo = subSmallPineappleClass.getMethod("getInfo");
            Annotation[] annotations = getInfo.getAnnotations();
            // @java.beans.Transient(value=true)
            for (Annotation annotation : annotations) {
                System.out.println(annotation.toString());
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    // 反射执行方法
    public static void test05() {
        Class<SmallPineapple> smallPineappleClass = SmallPineapple.class;
        try {
            Constructor<SmallPineapple> constructor = smallPineappleClass.getConstructor(String.class, Integer.class);
            SmallPineapple smallPineapple = constructor.newInstance("simon", 18);
            Method method = smallPineappleClass.getMethod("getInfo");
            method.invoke(smallPineapple, null);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


}
