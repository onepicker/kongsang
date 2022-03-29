package com.ks.member;

import org.junit.Test;

import java.util.Arrays;

public class MyTest {

    @Test
    public void test(){
        char[] chs = new char[4];
        chs[0] = 'a';
        chs[1] = 'b';
        chs[2] = 'c';
        chs[3] = 'd';
        System.out.println(Arrays.toString(chs));
    }
}
