package com.budai.dsschallenge.dto;


import com.budai.dsschallenge.data.ProductCode;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Order {
    private final String id;
    private final ProductCode code;
    private final int quantity;
    private final LocalDateTime deadline;
    private final int profit;
    private final int fine;
}