package com.example.demo.controller;

import com.example.demo.service.ConfigService;
import com.example.demo.service.ProductDaoService;
import com.example.demo.service.SalesDaoService;
import com.example.demo.util.EncoderDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@RestController
@RequestMapping("/home")
public class DataController {

    @Autowired
    ConfigService configService;
    @Autowired
    ProductDaoService productDaoService;
    @Autowired
    SalesDaoService salesDaoService;

    @GetMapping(path = "/ping")
    public String pingMethod()
    {
        String s = EncoderDecoder.getEncodedParam("123");
        //log.info("---------> "+ s);
        log.info("ENCODED VALUE IS -------> {}", EncoderDecoder.getEncodedParam("123"));
        log.info("DECODED VALUE IS -------> {}", EncoderDecoder.getDecodedParam(s));
        configService.prepareConfig();
        return "PING REQUEST IS WORKING !!!!";
    }

    @GetMapping(path = "/product")
    public String getProduct()
    {
        //productDaoService.getProductList();
        return productDaoService.getProductList().get(0).getProduct_name();
    }

    @GetMapping(path = "/sales")
    public Integer getSalesDetails()
    {
        log.info("PRICE OF SOLD ITEM FETCHED.......");
        return salesDaoService.getSalesDetails().get(0).getPrice();
    }

    @GetMapping(path = "/old/{productId}")
    public String getProductOld(@PathVariable("productId") @NotNull String productId)
    {
        log.info("PROFILE ID RECEIVED IN REQUEST ----> "+productId);

        return productDaoService.getProductListOld(productId).get(0).getProduct_name();
    }
}
