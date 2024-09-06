package com.e_commerce.security.Service;

import com.e_commerce.security.Entity.Products;
import com.e_commerce.security.Entity.UserData;
import com.e_commerce.security.Entity.UsersCodeCollection;
import com.e_commerce.security.Repository.ProductRepo;
import com.e_commerce.security.Repository.UserDataRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SellerService {

    ProductRepo productRepo;
    UserDataRepo userDataRepo;

    public String sellProduct(Products products){
        UserData usr =userDataRepo.findByName(products.getOwner());
        System.out.println(products.getProductName());
        List<UsersCodeCollection> ucc=usr.getProducts().stream().filter(
                (a)-> a.getProductName().equals(products.getProductName())
        ).toList();
        if(ucc.isEmpty()){
            return "Can't Sell";
        }
        String code=ucc.getFirst().getCodes().getFirst();
        products.setUniqCode(code);
        productRepo.save(products);
        return "Success";
    }
}
