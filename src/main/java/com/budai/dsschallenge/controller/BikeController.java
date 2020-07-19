package com.budai.dsschallenge.controller;

import com.budai.dsschallenge.dto.CalculationOutput;
import com.budai.dsschallenge.dto.Order;
import com.budai.dsschallenge.service.CsvReader;
import com.budai.dsschallenge.service.CsvWriter;
import com.budai.dsschallenge.service.OrderService;
import com.budai.dsschallenge.service.exception.CsvReadingException;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(BikeController.class);

    public BikeController(@Autowired OrderService orderService, @Autowired CsvReader csvReader) {
        this.orderService = orderService;
        this.csvReader = csvReader;
    }

    @Operation(summary = "Get optimal schedule for the input orders")
    @PostMapping("/optimal-ordering")
    public CalculationOutput getCalculation(@RequestParam("file") MultipartFile file) throws IOException, CsvReadingException {
        logger.info("calling optimal ordering");
        List<Order> orders = csvReader.readOrders(file);
        CalculationOutput result = orderService.calculateOptimalOrdering(orders);

        CsvWriter csvWriter = new CsvWriter();
        csvWriter.writeOrderOutputToCsv(result.getOrders());
        csvWriter.writeProgram(result.getProgram());
        return result;
    }
}
