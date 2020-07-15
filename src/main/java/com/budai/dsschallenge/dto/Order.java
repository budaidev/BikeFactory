package com.budai.dsschallenge.dto;


import com.budai.dsschallenge.data.ProductCode;
import com.opencsv.bean.CsvBindByName;
import lombok.Data;

import java.util.Date;

/*
https://attacomsian.com/blog/spring-boot-upload-parse-csv-file#
 */

@Data
public class Order {
    @CsvBindByName
    private String id;
    @CsvBindByName
    private ProductCode code;
    @CsvBindByName
    private int quantity;
    @CsvBindByName
    private Date deadline;
    @CsvBindByName
    private int profit;
    @CsvBindByName
    private int fine;
}
