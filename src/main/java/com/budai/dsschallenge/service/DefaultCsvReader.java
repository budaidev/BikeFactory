package com.budai.dsschallenge.service;

import com.budai.dsschallenge.data.ProductCode;
import com.budai.dsschallenge.dto.Order;
import com.budai.dsschallenge.service.exception.CsvReadingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

public class DefaultCsvReader implements CsvReader {

    Logger logger = LoggerFactory.getLogger(DefaultCsvReader.class);

    public static final String COMMA_DELIMITER = ",";
    public static final int COLUMN_NUMBERS = 6;

    @Override
    public List<Order> readOrders(MultipartFile orderFile) throws IOException, CsvReadingException {
        InputStream in = orderFile.getInputStream();
        return getOrders(in);
    }

    @Override
    public List<Order> readOrders(File orderFile) throws IOException, CsvReadingException {
        InputStream in = new FileInputStream(orderFile);
        return getOrders(in);
    }

    private List<Order> getOrders(InputStream in) throws IOException, CsvReadingException {
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
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                if (values.length < COLUMN_NUMBERS) {
                    throw new CsvReadingException(String.format("Too few columns found in row number %d", lineNumber));
                } else if (values.length < COLUMN_NUMBERS) {
                    throw new CsvReadingException(String.format("Too many columns found in row number %d", lineNumber));
                }

                LocalDateTime date = parseDateColumn(formatter, lineNumber, values[3]);
                String id = values[0];
                ProductCode code = getProductCode(values[1], lineNumber);
                int quantity = parseInteger(values[2], lineNumber, "quantity");
                int profit = parseInteger(values[4], lineNumber, "profit");
                int fine = parseInteger(values[5], lineNumber, "fine");

                orders.add(new Order(id, code, quantity, date, profit, fine));
                lineNumber++;
            }

        }
        return orders;
    }

    private int parseInteger(String value, int lineNumber, String columnName) throws CsvReadingException {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            throw new CsvReadingException(String.format("Invalid number format for column %s for the value %s in line number %d", columnName, value, lineNumber));
        }
    }

    private ProductCode getProductCode(String value, int lineNumber) throws CsvReadingException {
        try {
            return ProductCode.valueOf(value);
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            throw new CsvReadingException(String.format("Invalid product code for %s in line number %d", value, lineNumber));
        }
    }

    private LocalDateTime parseDateColumn(DateTimeFormatter formatter, int lineNumber, String value) throws CsvReadingException {
        LocalDateTime date;
        try {
            date = LocalDateTime.parse(value, formatter);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            throw new CsvReadingException(String.format("Invalid date format for %s in line number %d", value, lineNumber));
        }
        return date;
    }
}

