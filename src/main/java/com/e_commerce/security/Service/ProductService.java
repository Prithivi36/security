package com.e_commerce.security.Service;

import com.e_commerce.security.Entity.OwnerShipInfo;
import com.e_commerce.security.Entity.Products;
import com.e_commerce.security.Entity.UserData;
import com.e_commerce.security.Entity.UsersCodeCollection;
import com.e_commerce.security.Repository.OwnerShipRepo;
import com.e_commerce.security.Repository.ProductRepo;
import com.e_commerce.security.Repository.UserDataRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@AllArgsConstructor
@Service
public class ProductService {
    ProductRepo productRepo;
    OwnerShipRepo ownerShipRepo;
    UserDataRepo userDataRepo;

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
        OwnerShipInfo owi=new OwnerShipInfo();
        owi.setCode(uniqCode);
        owi.setDate(LocalDateTime.now());
        owi.setLastUpdated(LocalDateTime.now());
        owl.add("Manufacturer :"+products.getOwner());
        owi.setOwnerInfoList(owl);

        ownerShipRepo.save(owi);

        UserData userData=userDataRepo.findByName(products.getOwner());
        List<UsersCodeCollection> productsList=userData.getProducts();

        boolean found=false;

        for(UsersCodeCollection ucc : productsList){
            if(ucc.getProductName().equals(products.getProductName())){
                List<String> codes= ucc.getCodes();
                codes.addLast(uniqCode);
                found=true;
                break;
            }
        }
        if(!found) {
            productRepo.save(products);
            UsersCodeCollection newEntity = new UsersCodeCollection();
            List<String> codes = new ArrayList<>();
            codes.addLast(uniqCode);
            newEntity.setProductName(products.getProductName());
            newEntity.setCodes(codes);
            productsList.addLast(
                    newEntity
            );
        }

            userData.setProducts(productsList);
            userDataRepo.save(userData);


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


        String owner=product.getOwner();
        String productName=product.getProductName();
        UserData usd=userDataRepo.findByName(owner);

        UserData buyer=userDataRepo.findByName(user);
        List<UsersCodeCollection> buyerData=buyer.getProducts();

        boolean found1=false;

        for(UsersCodeCollection ucc : buyerData){
            if(ucc.getProductName().equals(productName)){
                List<String> codes= ucc.getCodes();
                codes.addLast(product.getUniqCode());
                found1=true;
                break;
            }
        }

        if(!found1){
            UsersCodeCollection newEntity=new UsersCodeCollection();
            List<String> codes=new ArrayList<>();
            codes.addLast(product.getUniqCode());
            newEntity.setProductName(productName);
            newEntity.setCodes(codes);
            buyerData.addLast(
                    newEntity
            );
        }
            buyer.setProducts(
                    buyerData
            );
            userDataRepo.save(buyer);



        List<UsersCodeCollection> ucc=(usd.getProducts());
        boolean found=false;
        for(UsersCodeCollection uc : ucc){
            if(uc.getProductName().equals(productName)){
                found=true;
                List<String> codes = uc.getCodes();
                codes.removeFirst();
                String newCode=codes.getFirst();
                product.setUniqCode(newCode);
                break;
            }
        }

        if(found) {
            usd.setProducts(ucc);
            userDataRepo.save(usd);
            product.set_id(null);
            productRepo.save(product);
        }


        return "That's your product now";
    }

    public List<Products> getAllProduct(){
        return productRepo.findAll();
    }

    public Products findById(String id){
        return productRepo.findById(id).orElse(new Products());
    }
}
