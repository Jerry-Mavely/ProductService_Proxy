package com.example.productservice_proxy;

import org.junit.jupiter.api.Test;

import java.util.Random;

public class RandomTest {

    @Test
    public void randomTest(){
        Random random = new Random();
        int i = random.nextInt(10);
        assert(i < 5);
    }
}
