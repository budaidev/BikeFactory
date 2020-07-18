package com.budai.dsschallenge.ordering;

import com.budai.dsschallenge.dto.CalculationOutput;
import com.budai.dsschallenge.dto.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderingStrategy {

    CalculationOutput calculateOptimalOrdering(List<Order> orders, LocalDateTime currentDate);

}
