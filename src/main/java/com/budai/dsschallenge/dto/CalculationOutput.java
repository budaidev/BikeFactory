package com.budai.dsschallenge.dto;

import lombok.Data;

import java.util.List;

@Data
public class CalculationOutput {
    final List<OrderOutput> orders;
    final List<Program> program;
}
