package com.e_commerce.security.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "products")
public class Products {
    private String _id;
    private String productName;
    private int price;
    private String owner;
    private String uniqCode;
    private String description;
    private String image;
}
