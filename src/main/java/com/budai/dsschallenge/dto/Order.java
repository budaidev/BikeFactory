package com.budai.dsschallenge.dto;


import com.budai.dsschallenge.data.ProductCode;
import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private String id;
    private ProductCode code;
    private int quantity;
    private Date deadline;
    private int profit;
    private int fine;
}
