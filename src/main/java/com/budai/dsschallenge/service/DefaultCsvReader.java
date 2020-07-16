package com.budai.dsschallenge.service;

import com.budai.dsschallenge.dto.Order;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

public class DefaultCsvReader implements CsvReader {
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
        try (Reader reader = new BufferedReader(new InputStreamReader(in))) {

            CsvToBean<Order> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Order.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean.parse();
        }
    }
}
