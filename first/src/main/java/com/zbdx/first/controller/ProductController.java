package com.bimowo.first.controller;


import com.bimowo.first.model.Product;
import com.bimowo.first.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Product> list(){
        return productService.listShowProduct();
    }


        @RequestMapping(value = "/queryItem",method = RequestMethod.GET)
                public Product query(Integer productId) {
        Product product = productService.findByProductId(productId);
          if (product != null) {
              return product;
          }else return new Product();

        }
    }

