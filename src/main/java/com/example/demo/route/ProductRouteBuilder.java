package com.example.demo.route;

import com.example.demo.data.ProductDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Expression;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductRouteBuilder extends RouteBuilder {

    ProductDetails msg;
    @Override
    public void configure() throws Exception {

        from("direct:testRoute")
                .routeId("productRoute")
                .process(exchange->{
                            msg = exchange.getIn().getBody(ProductDetails.class);
                            //exchange.getIn().getBody(String.class);
                            //log.info("MESSAGE BODY --> {}",msg.getName());
                            ObjectMapper objectMapper = new ObjectMapper();
                            String jsonString = objectMapper.writeValueAsString(msg);
                            exchange.getIn().setHeader("CamelHttpMethod", "POST");
                            exchange.getIn().setHeader("Content-Type", "application/json");
                            exchange.getIn().setBody(jsonString);
                            log.info("MESSAGE BODY \n     --> {}",jsonString);
                          }
                        ).convertBodyTo(String.class)
                
                .to("http://localhost:8080/bk/home/product")
                    //.log("Route msg received!!!  ${body}");
                .process(exchange->{
                    // Capture the response
                    String response = exchange.getIn().getBody(String.class);
                    // Set the response back to the exchange body
                    exchange.getIn().setBody(response);
                });
                

    }
}
