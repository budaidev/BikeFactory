package com.budai.dsschallenge.ordering;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MachinesTest {

    Machines machines;

    @BeforeEach
    public void setup() {
        machines = new Machines();

    }

    @Test
    public void testCuttingSimple() {
        int result = machines.scheduleCutting("MEGR001", 1, 5);
        assertEquals(5, result);
    }

    @Test
    public void testCutting() {
        int result = machines.scheduleCutting("MEGR001", 1000, 5);
        assertEquals(835, result);
    }
}
