package com.budai.dsschallenge.service;

import com.budai.dsschallenge.dto.Order;
import com.budai.dsschallenge.service.exception.CsvReadingException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface CsvReader {
    List<Order> readOrders(MultipartFile orderFile) throws IOException, CsvReadingException;

    List<Order> readOrders(File orderFile) throws IOException, CsvReadingException;
}
