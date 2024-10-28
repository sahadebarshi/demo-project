package com.example.demo.controller;


import com.example.demo.data.ProductDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.apache.camel.FluentProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@RestController
@RequestMapping("/home")
public class RouteController {

    @Autowired
    CamelContext camelContext;

    @GetMapping(path = "/productDetails")
    public String getProduct()
    {
        FluentProducerTemplate template = camelContext.createFluentProducerTemplate();
        //log.info("CAMEL CONTEXT OBJECT ... {}",camelContext);
        ProductDetails productDetails = new  ProductDetails();
        productDetails.setName("Router");
        productDetails.setManufacturer("JIO");
        productDetails.setPrice("1500");
        productDetails.setDescription("Jio Fiber router");
        template.withBody(productDetails).to("direct:testRoute").request(String.class);
        return "OK..";
    }

    @GetMapping(path = "/test", produces = MediaType.APPLICATION_JSON)
    public String getTestData()
    {
        ExecutorService threadPool = Executors.newFixedThreadPool(4);
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("HI THREAD 1");
        }, threadPool).thenRun(()->{
            log.info("HI THREAD 2");
        });
        if(completableFuture.isDone()) {
            log.info("CompletableFuture THREAD is executed.....");
            completableFuture.join();
        }
        log.info("REQUEST RECEIVED AT test CONTROLLER..........");
        String msg = "Task executed.";
        return "{ \"Status\" : \"OK\", \"Message\" : \""+msg+"\" }";
    }

    @PostMapping(path ="/product", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public Response submitProduct(HttpServletRequest request) throws IOException {
        log.info("MESSAGE BODY RECEIVED IN POST REQUEST \n{}",request.getReader().readLine());
        return Response.ok().build();
    }
}
