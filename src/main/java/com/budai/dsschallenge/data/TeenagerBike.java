package com.budai.dsschallenge.data;

public class TeenagerBike extends AbstractProduct {
    @Override
    public int cuttingTime() {
        return 6;
    }

    @Override
    public int bendingTime() {
        return 15;
    }

    @Override
    public int weldingTime() {
        return 10;
    }

    @Override
    public int testingTime() {
        return 5;
    }

    @Override
    public int paintingTime() {
        return 15;
    }

    @Override
    public int packingTime() {
        return 12;
    }
}
