package com.budai.dsschallenge.service;

import com.budai.dsschallenge.data.ProductCode;
import com.budai.dsschallenge.dto.Order;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

public class DefaultCsvReader implements CsvReader {

    public static final String COMMA_DELIMITER = ",";

    @Override
    public List<Order> readOrders(MultipartFile orderFile) throws IOException {
        InputStream in = orderFile.getInputStream();
        return getOrders(in);
    }

    @Override
    public List<Order> readOrders(File orderFile) throws IOException {
        InputStream in = new FileInputStream(orderFile);
        return getOrders(in);
    }

    private List<Order> getOrders(InputStream in) throws IOException {
        List<Order> orders = new ArrayList<>();
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("MM.dd. HH:mm")
                .parseDefaulting(ChronoField.YEAR, 2020)
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .toFormatter();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {

            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);

                LocalDateTime date = LocalDateTime.parse(values[3], formatter);
                date.withYear(2020);
                orders.add(new Order(values[0], ProductCode.valueOf(values[1]), Integer.parseInt(values[2]), date, Integer.parseInt(values[4]), Integer.parseInt(values[5])));
            }

        }
        return orders;
    }
}

