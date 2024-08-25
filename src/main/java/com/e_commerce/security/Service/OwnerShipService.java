package com.e_commerce.security.Service;

import com.e_commerce.security.Entity.OwnerShipInfo;
import com.e_commerce.security.Repository.OwnerShipRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class OwnerShipService {
    OwnerShipRepo ownerShipRepo;
    public OwnerShipInfo getOwnershipInfo(String code){
        return ownerShipRepo.findByCode(code);
    }
}
