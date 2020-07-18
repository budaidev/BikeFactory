package com.budai.dsschallenge.ordering;

import com.budai.dsschallenge.dto.CalculationOutput;
import com.budai.dsschallenge.dto.Order;
import com.budai.dsschallenge.ordering.comparators.OrderProfitComparator;
import com.budai.dsschallenge.service.DateUtil;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class GreedyOrderingStrategy implements OrderingStrategy {

    DateUtil dateUtil;

    public GreedyOrderingStrategy(DateUtil dateUtil) {
        this.dateUtil = dateUtil;
    }

    @Override
    public CalculationOutput calculateOptimalOrdering(List<Order> orders, LocalDateTime currentDate) {
        Collections.sort(orders, new OrderProfitComparator());
        return null;
    }
}


