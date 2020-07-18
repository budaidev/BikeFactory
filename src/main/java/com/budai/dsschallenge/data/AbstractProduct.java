package com.budai.dsschallenge.data;

import java.util.List;

public abstract class AbstractProduct implements Product {

    public List<Integer> getJobLengths() {
        return List.of(cuttingTime(), bendingTime(), weldingTime(), testingTime(), paintingTime(), packingTime());
    }
}