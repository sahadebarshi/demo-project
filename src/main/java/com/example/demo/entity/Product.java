package com.example.demo.entity;



import lombok.Data;

//import javax.persistence.*;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer product_id;

    private String product_name;

    private Integer unit_price;

}
