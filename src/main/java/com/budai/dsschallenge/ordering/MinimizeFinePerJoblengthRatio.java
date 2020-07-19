package com.budai.dsschallenge.ordering;

import com.budai.dsschallenge.dto.CalculationOutput;
import com.budai.dsschallenge.dto.Order;
import com.budai.dsschallenge.ordering.comparators.OrderLengthPerFineComparator;
import com.budai.dsschallenge.service.DateUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class MinimizeFinePerJoblengthRatio extends AbstractOrderingStrategy {

    DateUtil dateUtil;

    public MinimizeFinePerJoblengthRatio(DateUtil dateUtil) {
        this.dateUtil = dateUtil;
    }

    @Override
    public CalculationOutput calculateOptimalOrdering(List<Order> orders, LocalDateTime currentDate) {
        CalculationOutput result = null;
        int bestScore = Integer.MIN_VALUE;
        Collections.sort(orders, new OrderLengthPerFineComparator());
        List<Integer> deadLines = null;
        for (int i = 0; i < 50; i++) {
            List<Order> swapped = tryToOptimize(orders, deadLines);
            CalculationOutput current = processOrdersInOrder(swapped, currentDate, dateUtil);
            int sum = current.getOrders().stream().mapToInt(x -> x.getProfit()).sum();
            if (sum > bestScore) {
                bestScore = sum;
                result = current;
                orders = swapped;
                deadLines = current.getOrders().stream().mapToInt(o -> dateUtil.getDayDifferenceBetweenDates(o.getCompleteDate(), o.getOriginalDeadline())).boxed().collect(Collectors.toList());
            }
        }
        System.out.println(deadLines);
        return result;
    }

    public List<Order> tryToOptimize(List<Order> orders, List<Integer> deadlines) {
        if (deadlines == null) {
            return orders;
        }
        List<Integer> beforeDeadlines = new ArrayList<>();
        List<Integer> afterDeadlines = new ArrayList<>();
        for (int i = 0; i < deadlines.size(); i++) {
            if (deadlines.get(i) <= 0) {
                beforeDeadlines.add(i);
            } else {
                afterDeadlines.add(i);
            }
        }
        List<Order> result = new ArrayList<>(orders);
        if (beforeDeadlines.size() > 0 && afterDeadlines.size() > 0) {
            int random1 = new Random().nextInt(beforeDeadlines.size());
            int random2 = new Random().nextInt(afterDeadlines.size());
            Collections.swap(result, beforeDeadlines.get(random1), afterDeadlines.get(random2));
        } else {
            List<Integer> randoms = ThreadLocalRandom.current().ints(0, orders.size()).distinct().limit(2).boxed().collect(Collectors.toList());
            Collections.swap(result, randoms.get(0), randoms.get(1));
        }
        return result;

    }


}
