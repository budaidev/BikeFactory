package com.budai.dsschallenge.ordering;

import com.budai.dsschallenge.data.ProductCode;
import com.budai.dsschallenge.dto.CalculationOutput;
import com.budai.dsschallenge.dto.Order;
import com.budai.dsschallenge.service.DateProvider;
import com.budai.dsschallenge.service.DateUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MinimizeDeadlineStrategyTest {

    MinimizeDeadlineStrategy strategy;
    DateUtil dateUtil;
    DateProvider dateProvider;

    @BeforeEach
    public void setup() {
        dateProvider = new DateProvider();
        dateUtil = new DateUtil();
        strategy = new MinimizeDeadlineStrategy(dateUtil);
    }

    @Test
    public void test1() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("MEGR001", ProductCode.GYB, 1000, LocalDateTime.of(2020, 7, 21, 13, 0), 1500, 20000));
        CalculationOutput calc = strategy.calculateOptimalOrdering(orders, dateProvider.getCurrentDate());
        System.out.println(calc.getOrders());
    }

}
