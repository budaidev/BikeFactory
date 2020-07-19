package com.budai.dsschallenge.ordering;

import com.budai.dsschallenge.dto.CalculationOutput;
import com.budai.dsschallenge.dto.Order;
import com.budai.dsschallenge.service.DateUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BruteForceStrategy extends AbstractOrderingStrategy {

    DateUtil dateUtil;

    public BruteForceStrategy(DateUtil dateUtil) {
        this.dateUtil = dateUtil;
    }

    @Override
    public CalculationOutput calculateOptimalOrdering(List<Order> orders, LocalDateTime currentDate) {
        int maxProfit = Integer.MIN_VALUE;
        CalculationOutput bestOutput = null;
        for (List<Integer> indexes : permute(IntStream.range(0, orders.size()).toArray())) {
            List<Order> currentOrders = getCurrentOrder(indexes, orders);
            CalculationOutput output = processOrdersInOrder(currentOrders, currentDate, dateUtil);
            int profit = output.getOrders().stream().map(x -> x.getProfit()).mapToInt(x -> x).sum();
            if (profit > maxProfit) {
                maxProfit = profit;
                bestOutput = output;
            }
        }

        return bestOutput;
    }

    public List<Order> getCurrentOrder(List<Integer> indexes, List<Order> originalOrders) {
        return indexes.stream().map(x -> originalOrders.get(x)).collect(Collectors.toList());
    }

    public ArrayList<ArrayList<Integer>> permute(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

        //start from an empty list
        result.add(new ArrayList<Integer>());

        for (int i = 0; i < num.length; i++) {
            //list of list in current iteration of the array num
            ArrayList<ArrayList<Integer>> current = new ArrayList<ArrayList<Integer>>();

            for (ArrayList<Integer> l : result) {
                // # of locations to insert is largest index + 1
                for (int j = 0; j < l.size() + 1; j++) {
                    // + add num[i] to different locations
                    l.add(j, num[i]);

                    ArrayList<Integer> temp = new ArrayList<Integer>(l);
                    current.add(temp);

                    //System.out.println(temp);

                    // - remove num[i] add
                    l.remove(j);
                }
            }

            result = new ArrayList<ArrayList<Integer>>(current);
        }

        return result;
    }
}
