package com.budai.dsschallenge.ordering.comparators;

import com.budai.dsschallenge.data.BikeFactory;
import com.budai.dsschallenge.data.Product;
import com.budai.dsschallenge.data.ProductCode;
import com.budai.dsschallenge.dto.Order;

import java.util.Comparator;

public class OrderLengthPerFineComparator implements Comparator<Order> {

    @Override
    public int compare(Order o1, Order o2) {
        return compare((double) o2.getFine() / getOrderEstimatedLength(o2.getQuantity(), o2.getCode()), (double) o1.getFine() / getOrderEstimatedLength(o1.getQuantity(), o1.getCode()));
    }

    public int compare(double left, double right) {
        if (left < right) {
            return -1;
        } else if (right < left) {
            return 1;
        } else {
            return 0;
        }

    }

    public int getOrderEstimatedLength(int quantity, ProductCode code) {
        Product product = BikeFactory.createBike(code);
        return product.getMinimumMinutesToComplete(quantity);
    }
}