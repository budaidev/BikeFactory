package com.budai.dsschallenge.data;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractProduct implements Product {

    public List<Integer> getJobLengths() {
        return Arrays.asList(cuttingTime(), bendingTime(), weldingTime(), testingTime(), paintingTime(), packingTime());
    }
}