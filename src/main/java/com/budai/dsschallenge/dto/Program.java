package com.budai.dsschallenge.dto;


import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Program {
    private final LocalDate date;
    private final String machineId;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final String orderId;
}
