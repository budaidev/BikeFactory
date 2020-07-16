package com.budai.dsschallenge.ordering;

import com.budai.dsschallenge.dto.CalculationOutput;
import com.budai.dsschallenge.dto.Order;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class GreedyOrderingStrategy implements OrderingStrategy {
    @Override
    public CalculationOutput calculateOptimalOrdering(List<Order> orders, Date currentDate) {
        Collections.sort(orders, new OrderProfitComparator());
        return null;
    }
}

class OrderProfitComparator implements Comparator<Order> {

    @Override
    public int compare(Order o1, Order o2) {
        return o2.getProfit() - o1.getProfit();
    }
}
