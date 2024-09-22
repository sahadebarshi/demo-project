package com.example.demo.util;

import com.example.demo.service.SalesDaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class DataValidator {

    @Autowired
    SalesDaoService salesDaoService;

    @PostConstruct
    public void validateSalesData()
    {
        salesDaoService.validateParam();
        log.info("<<< Sales data validated >>>");
    }
}
