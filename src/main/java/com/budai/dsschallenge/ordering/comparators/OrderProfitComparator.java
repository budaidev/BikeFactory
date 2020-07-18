package com.budai.dsschallenge.ordering.comparators;

import com.budai.dsschallenge.dto.Order;

import java.util.Comparator;

public class OrderProfitComparator implements Comparator<Order> {

    @Override
    public int compare(Order o1, Order o2) {
        return o2.getProfit() - o1.getProfit();
    }
}