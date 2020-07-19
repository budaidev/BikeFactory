package com.budai.dsschallenge.dto;


import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderOutput {
    @CsvBindByPosition(position = 0)
    private final String id;
    @CsvBindByPosition(position = 1)
    private final int profit;
    @CsvBindByPosition(position = 2)
    private final int fine;
    @CsvBindByPosition(position = 3)
    private final LocalDateTime startDate;
    @CsvBindByPosition(position = 4)
    private final LocalDateTime completeDate;
    @CsvBindByPosition(position = 5)
    private final LocalDateTime originalDeadline;
}