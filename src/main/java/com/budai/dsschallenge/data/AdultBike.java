package com.budai.dsschallenge.data;


public class AdultBike implements Product {
    @Override
    public int cuttingTime() {
        return 8;
    }

    @Override
    public int bendingTime() {
        return 16;
    }

    @Override
    public int weldingTime() {
        return 12;
    }

    @Override
    public int testingTime() {
        return 5;
    }

    @Override
    public int paintingTime() {
        return 20;
    }

    @Override
    public int packingTime() {
        return 15;
    }
}
