package com.bimowo.first.service;

import com.bimowo.first.model.Product;
import com.bimowo.first.utils.CommonQueryBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductService {
    /**
     * 查询全部的商品信息.
     */
    List<Product> listShowProduct();
    /**
     * 根据productId查询商品信息.
     */
    Product findByProductId(Integer productId);

    /**
     * list分页查询
     */
    List<Product> list4Page (Product record,@Param("commonQueryParam")CommonQueryBean query);

    long count(@Param(value = "record")Product record);

    int save(Product product);


    Product selectByPrimaryKey (@Param("id") Long id );

    void updateItem(Product product);

    int deleteByPrimaryKey(Long id);
}
