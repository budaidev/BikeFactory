package com.budai.dsschallenge.controller;

import com.budai.dsschallenge.dto.CalculationOutput;
import com.budai.dsschallenge.dto.Order;
import com.budai.dsschallenge.service.CsvReader;
import com.budai.dsschallenge.service.OrderService;
import com.budai.dsschallenge.service.exception.CsvReadingException;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/")
public class BikeController {

    OrderService orderService;
    CsvReader csvReader;

    public BikeController(@Autowired OrderService orderService, @Autowired CsvReader csvReader) {
        this.orderService = orderService;
        this.csvReader = csvReader;
    }

    @Operation(summary = "Get optimal schedule for the input orders")
    @PostMapping("/optimal-ordering")
    public CalculationOutput getCalculation(@RequestParam("file") MultipartFile file) throws IOException, CsvReadingException {
        List<Order> orders = csvReader.readOrders(file);
        return orderService.calculateOptimalOrdering(orders);
    }
}
