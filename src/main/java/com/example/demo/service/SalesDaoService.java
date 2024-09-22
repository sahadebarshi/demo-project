package com.example.demo.service;



import com.example.demo.entity.Sales;

import java.util.List;

public interface SalesDaoService {

    public List<Sales> getSalesDetails();
    public void validateParam();
}
