package com.simon.reference;

import java.beans.Transient;

public class SubSmallPineapple extends Pineapple{
    @Override
    @Transient
    public void getInfo() {
        System.out.println("hello!");
    }
}
