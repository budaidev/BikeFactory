package com.budai.dsschallenge.data;

import com.budai.dsschallenge.configuration.MachineNumber;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractProduct implements Product {

    public List<Integer> getJobLengths() {
        return Arrays.asList(cuttingTime(), bendingTime(), weldingTime(), testingTime(), paintingTime(), packingTime());
    }

    public int getMinimumMinutesToComplete(int quantity) {
        int productionTime = getJobLengths().stream().mapToInt(x -> x).sum();
        double minutesbetweenproducts = Arrays.asList(
                (double) cuttingTime() / MachineNumber.CUTTING,
                (double) bendingTime() / MachineNumber.BENDING,
                (double) weldingTime() / MachineNumber.WELDING,
                (double) testingTime() / MachineNumber.TESTING,
                (double) paintingTime() / MachineNumber.PAINTING,
                (double) packingTime() / MachineNumber.PACKING).stream().mapToDouble(x -> x).max().getAsDouble();
        return productionTime + (int) ((quantity - 1) * minutesbetweenproducts);

    }
}