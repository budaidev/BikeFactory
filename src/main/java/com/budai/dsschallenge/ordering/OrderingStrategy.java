package com.budai.dsschallenge.ordering;

import com.budai.dsschallenge.dto.CalculationOutput;
import com.budai.dsschallenge.dto.Order;

import java.util.Date;
import java.util.List;

public interface OrderingStrategy {

    CalculationOutput calculateOptimalOrdering(List<Order> orders, Date currentDate);

}
