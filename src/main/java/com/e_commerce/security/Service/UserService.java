package com.e_commerce.security.Service;

import com.e_commerce.security.Entity.UserData;
import com.e_commerce.security.Repository.UserDataRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {
    UserDataRepo usdr;
    public String saveUser(UserData data){
        usdr.save(data);
        return "Success";
    }
}
