package com.budai.dsschallenge.dto;


import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Program {
    @CsvBindByPosition(position = 0)
    private final LocalDate date;
    @CsvBindByPosition(position = 1)
    private final String machineId;
    @CsvBindByPosition(position = 2)
    private final LocalDateTime startTime;
    @CsvBindByPosition(position = 3)
    private final LocalDateTime endTime;
    @CsvBindByPosition(position = 4)
    private final String orderId;
}
