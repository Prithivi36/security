package com.e_commerce.security.Controller;

import com.e_commerce.security.Entity.OwnerShipInfo;
import com.e_commerce.security.Service.OwnerShipService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class OwnershipController {

    OwnerShipService ownerShipService;

    @GetMapping("/own/{code}")
    public OwnerShipInfo getOwnerShipInfo(@PathVariable String code){
        return ownerShipService.getOwnershipInfo(code);
    }
}
