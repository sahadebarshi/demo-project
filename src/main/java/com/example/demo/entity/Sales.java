package com.example.demo.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "sales")
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seller_id;

    private Integer product_id;

    private Integer buyer_id;

    private Date sale_date;

    private Integer quantity;

    private Integer price;
}
