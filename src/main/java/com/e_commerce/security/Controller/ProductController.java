package com.e_commerce.security.Controller;

import com.e_commerce.security.Entity.Products;
import com.e_commerce.security.Service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProductController {
    ProductService productService;

    @PostMapping("/new")
    public ResponseEntity<String> saveNewProduct(@RequestBody Products products){
        return ResponseEntity.ok(productService.saveProduct(products));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Products>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Products> findProduct(@PathVariable String id){
        return ResponseEntity.ok(productService.findById(id));
    }

    @PutMapping("/buy/{id}/{username}")
    public ResponseEntity<String> buyProduct(@PathVariable("id") String id,@PathVariable("username") String username){
        return ResponseEntity.ok(productService.buyProduct(id,username));
    }
}
