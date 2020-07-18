package com.budai.dsschallenge.data;

public class BikeFactory {

    public static Product createBike(ProductCode type) {
        switch (type) {
            case FB:
                return new AdultBike();
            case SB:
                return new TeenagerBike();
            case GYB:
                return new ChildBike();
            default:
                throw new IllegalArgumentException("Unknown product");
        }
    }
}
