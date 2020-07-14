package com.budai.dsschallenge.dto;


import lombok.Data;

import java.util.Date;

@Data
public class OrderOutput {
    private String id;
    private int profit;
    private int fine;
    private Date startDate;
    private Date completeDate;
    private Date originalDeadline;
}
