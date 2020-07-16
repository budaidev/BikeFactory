package com.budai.dsschallenge.service;

import com.budai.dsschallenge.dto.CalculationOutput;
import com.budai.dsschallenge.dto.Order;

import java.util.List;

public interface OrderService {

    CalculationOutput calculateOptimalOrdering(List<Order> orders);
}
