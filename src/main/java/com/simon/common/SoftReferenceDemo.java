package com.simon.common;

import com.simon.pojo.Student;

import java.lang.ref.SoftReference;

public class SoftReferenceDemo {

    public static void main(String[] args) {
        Student simon = Student.builder().name("simon").age(18).build();
        SoftReference<Student> studentSoftReference = new SoftReference<>(simon);
        System.out.println(studentSoftReference.get());
    }

}
