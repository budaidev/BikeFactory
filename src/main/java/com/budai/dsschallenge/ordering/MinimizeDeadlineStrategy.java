package com.budai.dsschallenge.ordering;

import com.budai.dsschallenge.dto.CalculationOutput;
import com.budai.dsschallenge.dto.Order;
import com.budai.dsschallenge.ordering.comparators.OrderDeadlineComparator;
import com.budai.dsschallenge.service.DateUtil;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class MinimizeDeadlineStrategy extends AbstractOrderingStrategy {

    DateUtil dateUtil;

    public MinimizeDeadlineStrategy(DateUtil dateUtil) {
        this.dateUtil = dateUtil;
    }

    @Override
    public CalculationOutput calculateOptimalOrdering(List<Order> orders, LocalDateTime currentDate) {
        Collections.sort(orders, new OrderDeadlineComparator());
        return processOrdersInOrder(orders, currentDate, dateUtil);
    }


}
