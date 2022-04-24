package com.simon.reference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmallPineapple {
    public String name;

    public Integer age;

    private double weight;

    public SmallPineapple(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public void getInfo() {
        System.out.println("info : name is " + name + " age is " + age + " weight is " + weight);
    }
}
