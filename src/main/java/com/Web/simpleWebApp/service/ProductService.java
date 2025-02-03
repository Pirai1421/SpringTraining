package com.Web.simpleWebApp.service;
import com.Web.simpleWebApp.model.Product;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
public class ProductService {
    public ProductService(){

    }
    List<Product> products=Arrays.asList(
            new Product(101,"iphone",5000),
            new Product(102,"watch",100),
            new Product(103,"camera",10000)
    );
    public List<Product> getProducts(){
        return products;
    }
    public Product getProductById(int prodId){
        return products.stream().filter(p -> p.getProductId()==prodId).findFirst().get();
    }


    public void addProduct(Product prod) {
        products.add(prod);
    }
    public void updateProduct(Product prod){
        int index=0;
        for(int i=0;i<products.size();i++){
            if(products.get(i).getProductId()==prod.getProductId()){
                index=i;
            }
            products.set(index,prod);
        }
    }
    public void deleteProduct(int ProductId){
        int index=0;
        for (int i=0;i<products.size();i++){
            if (products.get(i).getProductId()==ProductId){
                index=i;
            }
            products.remove(index);
        }
    }
}
