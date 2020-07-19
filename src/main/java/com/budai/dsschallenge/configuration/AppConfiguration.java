package com.budai.dsschallenge.configuration;

import com.budai.dsschallenge.ordering.MinimizeFinePerJoblengthRatio;
import com.budai.dsschallenge.ordering.OrderingStrategy;
import com.budai.dsschallenge.service.CsvReader;
import com.budai.dsschallenge.service.DateProvider;
import com.budai.dsschallenge.service.DateUtil;
import com.budai.dsschallenge.service.DefaultCsvReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    public DateUtil dateUtil() {
        return new DateUtil();
    }

    @Bean
    public OrderingStrategy orderingStrategy() {
        return new MinimizeFinePerJoblengthRatio(dateUtil());
    }

    @Bean
    public CsvReader csvReader() {
        return new DefaultCsvReader();
    }

    @Bean
    public DateProvider dateProvider() {
        return new DateProvider();
    }
}
