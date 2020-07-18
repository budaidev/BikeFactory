package com.budai.dsschallenge.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderOutput {
    private final String id;
    private final int profit;
    private final int fine;
    private final LocalDateTime startDate;
    private final LocalDateTime completeDate;
    private final LocalDateTime originalDeadline;
}
