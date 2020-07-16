package com.budai.dsschallenge.dto;


import lombok.Data;

import java.util.Date;

@Data
public class OrderOutput {
    private final String id;
    private final int profit;
    private final int fine;
    private final Date startDate;
    private final Date completeDate;
    private final Date originalDeadline;
}
