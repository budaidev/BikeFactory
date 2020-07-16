package com.budai.dsschallenge.service;

import com.budai.dsschallenge.dto.Order;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvReaderTest {

    CsvReader csvReader;

    @BeforeEach
    public void setup(){
        csvReader = new DefaultCsvReader();
    }

    @Test
    public void readCsvTest() throws IOException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("testorders.csv")).getFile());
        List<Order> orders = csvReader.readOrders(file);

        assertEquals(15, orders.size());
        System.out.println(orders);
    }
}
