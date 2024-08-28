package com.e_commerce.security.Repository;

import com.e_commerce.security.Entity.UserData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataRepo extends MongoRepository<UserData,String> {
    UserData findByName(String name);
}
