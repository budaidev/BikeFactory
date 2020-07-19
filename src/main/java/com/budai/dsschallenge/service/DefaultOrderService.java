package com.budai.dsschallenge.service;

import com.budai.dsschallenge.dto.CalculationOutput;
import com.budai.dsschallenge.dto.Order;
import com.budai.dsschallenge.ordering.OrderingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultOrderService implements OrderService {

    OrderingStrategy orderingStrategy;
    DateProvider dateProvider;

    public DefaultOrderService(@Autowired OrderingStrategy orderingStrategy, @Autowired DateProvider dateProvider) {
        this.orderingStrategy = orderingStrategy;
        this.dateProvider = dateProvider;
    }


    @Override
    public CalculationOutput calculateOptimalOrdering(List<Order> orders) {
        return orderingStrategy.calculateOptimalOrdering(orders, dateProvider.getCurrentDate());
    }
}
