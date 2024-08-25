package com.e_commerce.security.Repository;

import com.e_commerce.security.Entity.Products;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends MongoRepository<Products,String> {
}
