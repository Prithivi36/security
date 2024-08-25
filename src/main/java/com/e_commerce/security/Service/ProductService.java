package com.e_commerce.security.Service;

import com.e_commerce.security.Entity.OwnerShipInfo;
import com.e_commerce.security.Entity.Products;
import com.e_commerce.security.Repository.OwnerShipRepo;
import com.e_commerce.security.Repository.ProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@AllArgsConstructor
@Service
public class ProductService {
    ProductRepo productRepo;
    OwnerShipRepo ownerShipRepo;

    public  String generateRandomCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder code = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(characters.length());
            code.append(characters.charAt(index));
        }

        return code.toString();
    }

    public String saveProduct(Products products){
        String uniqCode=generateRandomCode();
        List<String> owl=new LinkedList<>();
        products.setUniqCode(uniqCode);
        productRepo.save(products);
        OwnerShipInfo owi=new OwnerShipInfo();
        owi.setCode(uniqCode);
        owi.setDate(LocalDateTime.now());
        owl.add("Manufacturer :"+products.getOwner());
        owi.setOwnerInfoList(owl);

        ownerShipRepo.save(owi);

        return "Successfully saved , your code is :" +uniqCode;
    }

    public List<Products> getAllProduct(){
        return productRepo.findAll();
    }

    public Products findById(String id){
        return productRepo.findById(id).orElse(new Products());
    }
}
