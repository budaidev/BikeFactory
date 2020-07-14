package com.budai.dsschallenge.data;

/*

GYB - gyerek bicikli
5
10
8
5
12
10
FB - felnőtt bicikli
8
16
12
5
20
15
SB - serdülő bicikli
6
15
10
5
15
12

 */
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
