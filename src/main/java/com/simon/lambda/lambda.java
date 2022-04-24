package com.simon.lambda;

import com.google.common.collect.Lists;
import com.simon.pojo.Student;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class lambda {
    public static void main(String[] args) {
        test09();
    }

    // 方法引用、foreach
    public static void test01() {
        // Consumer是定义的lambda表达式的目标类型
        // Consumer<String> tConsumer = a -> System.out.println(a);
        Consumer<String> tConsumer = System.out::println;
        List<String> strings = new ArrayList<>();
        strings.add("java");
        strings.add("c++");
        /*
        1.可以将lambda作为参数传递（将行为进行传递，就像传递参数值一样）
        2.上面定义的lambda并没有执行输出，而是在下面中进行实际输出的动作-惰性求值的特性
         */
        strings.forEach(tConsumer);
    }

    // predicate reduce
    public static void test02() {
        List<String> strings = new ArrayList<>();
        strings.add("java");
        strings.add("c++");

        Predicate<String> predicate = name -> name.length() > 3;
        Set<String> collect = strings.stream().filter(predicate).collect(Collectors.toSet());
        collect.forEach(System.out::println);

        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        Optional<Integer> reduce = costBeforeTax.stream().reduce(Integer::sum);
        System.out.println(reduce.get());
    }

    // 过滤后使用特定字符拼接成字符串
    public static void test03() {
        List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.", "Canada");
        String collect = G7.stream().filter(s -> s.length() > 3).collect(Collectors.joining(","));
        System.out.println(collect);
    }

    // flatMap:合并多个stream
    public static void test04() {
        List<Integer> collect = Stream.of(Lists.newArrayList(1, 2, 3), Lists.newArrayList(4, 5, 6))
                .flatMap(a -> a.stream())
                .collect(Collectors.toList());

        collect.forEach(System.out::println);
    }

    // summaryStatistics:统计相关
    public static void test05() {
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics intSummaryStatistics = primes.stream().mapToInt(x -> x).summaryStatistics();
        System.out.println(intSummaryStatistics.getMax());
        System.out.println(intSummaryStatistics.getAverage());
    }

    // 自定义函数式接口，函数是接口作为lambda表达式的目标类型
    public static void test06() {
        MyInterface myInterface = () -> System.out.println("I love you");
        myInterface.study();
    }

    // 分组groupingBy
    public static void test07() {
        List<Student> students = Lists.newArrayList(Student.builder().name("simon").age(18).build()
                , Student.builder().name("simon").age(20).build(), Student.builder().name("yemen").age(33).build());

        Map<String, List<Student>> collect = students.stream().collect(Collectors.groupingBy(Student::getName));
        System.out.println(collect.toString());
    }

    // list -> map   Duplicate key 18
    public static void test08() {
        List<Student> students = Lists.newArrayList(Student.builder().name("simon").age(18).build()
                , Student.builder().name("simon").age(20).build(), Student.builder().name("yemen").age(33).build());
        // (k1, k2) -> k2 避免Duplicate key 18，用k2覆盖k1
        Map<String, Integer> collect = students.stream().collect(Collectors.toMap(Student::getName, Student::getAge,
                (k1, k2) -> k2));
        System.out.println(collect);
    }

    // match
    public static void test09(){
        List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.", "Canada");
        boolean france = G7.stream().anyMatch(x -> x.equals("France"));
        System.out.println(france);
    }

}
