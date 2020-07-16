package com.budai.dsschallenge.dto;

import com.budai.dsschallenge.data.ProductCode;
import com.opencsv.bean.AbstractBeanField;

public class ProductCodeConverter extends AbstractBeanField<ProductCode, ProductCode> {
    @Override
    protected Object convert(String value){
        return ProductCode.valueOf(value);
    }
}
