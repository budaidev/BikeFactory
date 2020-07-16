package com.budai.dsschallenge.dto;

import lombok.Data;

@Data
public class MachineUsage {
    private final int startTime;
    private final int endTime;
    private final String orderId;
    private final String machineId;


}
