package com.budai.dsschallenge.dto;


import com.budai.dsschallenge.data.ProductCode;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.opencsv.bean.CsvDate;
import lombok.Data;

import java.util.Date;

/*
https://attacomsian.com/blog/spring-boot-upload-parse-csv-file#
 */

@Data
public class Order {
    @CsvBindByName(column = "Azonosító")
    private String id;
    @CsvCustomBindByName(column = "Termék", converter=ProductCodeConverter.class)
    private ProductCode code;
    @CsvBindByName(column = "Darabszám")
    private int quantity;
    @CsvBindByName(column = "Határidő")
    @CsvDate("dd.MM. hh:mm")
    private Date deadline;
    @CsvBindByName(column = "Profit/db (Ft)")
    private int profit;
    @CsvBindByName(column = "Késési büntetés/nap (össz) (Ft)")
    private int fine;
}