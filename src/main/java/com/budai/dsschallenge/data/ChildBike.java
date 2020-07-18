package com.budai.dsschallenge.data;

public class ChildBike extends AbstractProduct {
    @Override
    public int cuttingTime() {
        return 5;
    }

    @Override
    public int bendingTime() {
        return 10;
    }

    @Override
    public int weldingTime() {
        return 8;
    }

    @Override
    public int testingTime() {
        return 5;
    }

    @Override
    public int paintingTime() {
        return 12;
    }

    @Override
    public int packingTime() {
        return 10;
    }
}
