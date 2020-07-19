package com.budai.dsschallenge.data;

import java.util.List;

public interface Product {

    int cuttingTime();

    int bendingTime();

    int weldingTime();

    int testingTime();

    int paintingTime();

    int packingTime();

    List<Integer> getJobLengths();

    int getMinimumMinutesToComplete(int quantity);

}
