package com.budai.dsschallenge.ordering.comparators;

import com.budai.dsschallenge.dto.Order;

import java.util.Comparator;

public class OrderDeadlineComparator implements Comparator<Order> {

    @Override
    public int compare(Order o1, Order o2) {
        return o1.getDeadline().compareTo(o2.getDeadline());
    }
}