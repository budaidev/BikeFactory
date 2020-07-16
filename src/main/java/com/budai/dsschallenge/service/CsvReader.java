package com.budai.dsschallenge.service;

import com.budai.dsschallenge.dto.Order;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface CsvReader {
    List<Order> readOrders(MultipartFile orderFile) throws IOException;

    List<Order> readOrders(File orderFile) throws IOException;
}
