package com.e_commerce.security.Controller;

import com.e_commerce.security.Entity.UserData;
import com.e_commerce.security.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserServiceController {
    UserService usrService;
    @PostMapping("/new/usr")
    public String saveUser(@RequestBody UserData userData){
        return usrService.saveUser(userData);
    }
}
