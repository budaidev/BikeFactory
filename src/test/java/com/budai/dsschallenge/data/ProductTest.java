package com.budai.dsschallenge.data;

import org.junit.jupiter.api.Test;

public class ProductTest {

    @Test
    public void testMinimumTimeToCompleteAdultBike() {
        Product product = new AdultBike();
        int result = product.getMinimumMinutesToComplete(1);
        System.out.println(result);
    }

    @Test
    public void testMinimumTimeToCompleteTeenagerBike() {
        Product product = new TeenagerBike();
        int result = product.getMinimumMinutesToComplete(142);
        System.out.println(result);
    }
}
