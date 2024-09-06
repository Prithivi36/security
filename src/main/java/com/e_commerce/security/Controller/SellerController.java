package com.e_commerce.security.Controller;

import com.e_commerce.security.Entity.Products;
import com.e_commerce.security.Service.SellerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SellerController {

    SellerService sellerService;

    @PostMapping("/sell")
    public String sellProduct(@RequestBody Products products){
        return sellerService.sellProduct(products);
    }
}
