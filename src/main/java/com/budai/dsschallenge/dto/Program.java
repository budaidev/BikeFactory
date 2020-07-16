package com.budai.dsschallenge.dto;


import lombok.Data;

import java.util.Date;

@Data
public class Program {
    private final Date date;
    private final String machineId;
    private final Date startTime;
    private final Date endTime;
    private final String orderId;
}
