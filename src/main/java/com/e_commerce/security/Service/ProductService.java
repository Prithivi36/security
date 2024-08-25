package com.e_commerce.security.Service;

import com.e_commerce.security.Entity.OwnerShipInfo;
import com.e_commerce.security.Entity.Products;
import com.e_commerce.security.Repository.OwnerShipRepo;
import com.e_commerce.security.Repository.ProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@AllArgsConstructor
@Service
public class ProductService {
    ProductRepo productRepo;
    OwnerShipRepo ownerShipRepo;

    public static String generateAlphanumericCode(int length) {
        String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(ALPHANUMERIC.charAt(random.nextInt(ALPHANUMERIC.length())));
        }
        return sb.toString();
    }

    public String saveProduct(Products products){
        String uniqCode=generateAlphanumericCode(16);
        List<String> owl=new LinkedList<>();
        products.setUniqCode(uniqCode);
        productRepo.save(products);
        OwnerShipInfo owi=new OwnerShipInfo();
        owi.setCode(uniqCode);
        owi.setDate(LocalDateTime.now());
        owi.setLastUpdated(LocalDateTime.now());
        owl.add("Manufacturer :"+products.getOwner());
        owi.setOwnerInfoList(owl);

        ownerShipRepo.save(owi);

        return "Successfully saved , your code is :" +uniqCode;
    }

    public String buyProduct(String id,String user){
        Products product=productRepo.findById(id).orElse(new Products());
        String code=product.getUniqCode();
        OwnerShipInfo owi=ownerShipRepo.findByCode(code);
        List<String> owl=owi.getOwnerInfoList();
        owi.setLastUpdated(LocalDateTime.now());
        owl.addLast("Owner ("+owl.size()+") :"+user);
        ownerShipRepo.save(owi);
        productRepo.deleteById(id);
        return "That's your product now";
    }

    public List<Products> getAllProduct(){
        return productRepo.findAll();
    }

    public Products findById(String id){
        return productRepo.findById(id).orElse(new Products());
    }
}
