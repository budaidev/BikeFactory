package com.budai.dsschallenge.service;

import com.budai.dsschallenge.dto.Order;
import com.budai.dsschallenge.service.exception.CsvReadingException;
import org.junit.jupiter.api.Assertions;
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
    public void readCsvTest() throws IOException, CsvReadingException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("testorders.csv")).getFile());
        List<Order> orders = csvReader.readOrders(file);

        assertEquals(15, orders.size());
        System.out.println(orders);
    }

    @Test
    public void readCsvMissingColumn() throws IOException, CsvReadingException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("missingcolumn.csv")).getFile());
        Assertions.assertThrows(CsvReadingException.class, () -> {
            List<Order> orders = csvReader.readOrders(file);
        });
    }

    @Test
    public void readCsvBadProductCode() throws IOException, CsvReadingException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("badproductcode.csv")).getFile());
        Assertions.assertThrows(CsvReadingException.class, () -> {
            List<Order> orders = csvReader.readOrders(file);
        });
    }

    @Test
    public void readCsvBadQuantity() throws IOException, CsvReadingException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("idquantityswap.csv")).getFile());
        Assertions.assertThrows(CsvReadingException.class, () -> {
            List<Order> orders = csvReader.readOrders(file);
        });
    }
}