package com.budai.dsschallenge.functional;

import com.budai.dsschallenge.dto.CalculationOutput;
import com.budai.dsschallenge.dto.Order;
import com.budai.dsschallenge.dto.OrderOutput;
import com.budai.dsschallenge.ordering.BruteForceStrategy;
import com.budai.dsschallenge.ordering.MinimizeDeadlineStrategy;
import com.budai.dsschallenge.ordering.MinimizeFineStrategy;
import com.budai.dsschallenge.service.CsvReader;
import com.budai.dsschallenge.service.DateProvider;
import com.budai.dsschallenge.service.DateUtil;
import com.budai.dsschallenge.service.DefaultCsvReader;
import com.budai.dsschallenge.service.exception.CsvReadingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class TestOrderingStrategies {
    MinimizeDeadlineStrategy strategy;
    DateUtil dateUtil;
    DateProvider dateProvider;
    CsvReader csvReader;

    @BeforeEach
    public void setup() {
        dateProvider = new DateProvider();
        dateUtil = new DateUtil();
        strategy = new MinimizeDeadlineStrategy(dateUtil);
        csvReader = new DefaultCsvReader();
    }

    @Test
    public void test1() throws IOException, CsvReadingException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("testorders.csv")).getFile());
        List<Order> orders = csvReader.readOrders(file);
        CalculationOutput calc = strategy.calculateOptimalOrdering(orders, dateProvider.getCurrentDate());
        int sum = 0;
        for (OrderOutput out : calc.getOrders()) {
            System.out.println(out);
            sum += out.getProfit();
        }
        System.out.println(sum);
    }

    @Test
    public void test2() throws IOException, CsvReadingException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("testorders.csv")).getFile());
        List<Order> orders = csvReader.readOrders(file);
        CalculationOutput calc = new MinimizeFineStrategy(dateUtil).calculateOptimalOrdering(orders, dateProvider.getCurrentDate());
        int sum = 0;
        for (OrderOutput out : calc.getOrders()) {
            System.out.println(out);
            sum += out.getProfit();
        }
        System.out.println(sum);
    }

    public void test3() throws IOException, CsvReadingException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("testorders.csv")).getFile());
        List<Order> orders = csvReader.readOrders(file);
        CalculationOutput calc = new BruteForceStrategy(dateUtil).calculateOptimalOrdering(orders, dateProvider.getCurrentDate());
        int sum = 0;
        for (OrderOutput out : calc.getOrders()) {
            System.out.println(out);
            sum += out.getProfit();
        }
        System.out.println(sum);
    }
}
