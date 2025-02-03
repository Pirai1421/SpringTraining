package com.Web.simpleWebApp.controller;
import com.Web.simpleWebApp.model.Product;
import com.Web.simpleWebApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class ProductController {

    @Autowired
    ProductService service;

    @GetMapping("/products")
    public List<Product> getProducts(){
        System.out.println(service.getProducts());
        return service.getProducts();
    }
    @GetMapping ("/products/{prodId}")
    public Product getProductById(@PathVariable int prodId){
        return service.getProductById(prodId);
    }
    @PostMapping("/products")
    public void addProduct(@RequestBody Product prod){
        service.addProduct(prod);
    }
    @DeleteMapping("/product/{ProdId}")
    public void DeleteProduct(@PathVariable int ProdId){
        service.deleteProduct(ProdId);
    }
}
