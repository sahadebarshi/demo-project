package com.example.demo.controller;

import com.example.demo.service.ConfigService;
import com.example.demo.service.ProductDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class DataController {

    @Autowired
    ConfigService configService;
    @Autowired
    ProductDaoService productDaoService;

    @GetMapping(path = "/ping")
    public String pingMethod()
    {
        configService.prepareConfig();
        return "PING REQUEST IS WORKING !!!!";
    }

    @GetMapping(path = "/product")
    public String getProduct()
    {
        //productDaoService.getProductList();
        return productDaoService.getProductList().get(0).getProduct_name();
    }
}
