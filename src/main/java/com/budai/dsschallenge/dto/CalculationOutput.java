package com.budai.dsschallenge.dto;

import lombok.Data;

import java.util.List;

@Data
public class CalculationOutput {
    List<OrderOutput> orders;
    List<Program> program;
}
