package com.e_commerce.security.Repository;

import com.e_commerce.security.Entity.OwnerShipInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerShipRepo extends MongoRepository<OwnerShipInfo,String> {
    OwnerShipInfo findByCode(String code);
}
