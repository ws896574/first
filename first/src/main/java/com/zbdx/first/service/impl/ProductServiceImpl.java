package com.bimowo.first.service.impl;

import com.bimowo.first.dao.ProductDao;
import com.bimowo.first.model.Product;
import com.bimowo.first.service.ProductService;
import com.bimowo.first.utils.CommonQueryBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Override
    public List<Product> listShowProduct() {
        Product record = new Product();
        record.setState(1);
        return productDao.list(record);
    }
    @Override
    public Product findByProductId(Integer productId) {
        Assert.notNull(productId, "productId is null!");
        Product record = new Product();
        record.setProductId(productId);
        List<Product> list = productDao.list(record);
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<Product> list4Page( Product record,@Param("commonQueryParam") CommonQueryBean query) {

        return productDao.list4Page(record,query);
    }

    @Override
    public long count(@Param(value = "record")Product record) {
        return productDao.count(record);
    }

    @Override
    public int save(Product record) {
        //商品名称不能为空，productId不能为空
        Assert.notNull(record.getName(),"name对象不能为空");
        Assert.notNull(record.getProductId(),"productId不能为空");
        //验证productId唯一，即productId在数据库中不存在
        Product searchItem = new Product();
        searchItem.setProductId(record.getProductId());
        long count = productDao.count(searchItem);
        if (count>0){
            //productId不唯一
            return -1;
        }
        Date date = new Date();
        record.setCreateTime(date);
        record.setUpdateTime(date);
        record.setState(1);
        return productDao.insert(record);
    }



    @Override
    public Product selectByPrimaryKey(Long id) {
        return productDao.selectByPrimaryKey(id);
    }

    @Override
    public void updateItem(Product product) {
        Product record = new Product();
        //更新的是按照主键进行更新的，所以主键不能为空
        Assert.notNull(record.getId(), "id不能为空");
        //因为updateByPrimaryKeySelective方法会把有的字段都更新，所以为了避免这种情况，实际项目中都是用新的对象更新
        Product updateItem = new Product();
        updateItem.setId(record.getId());
        //需求里面只更新userName,产品描述和产品展示url
        if (StringUtils.isNotEmpty(record.getName())) {
            updateItem.setName(record.getName());
        }
        if (StringUtils.isNotEmpty(record.getDesc())) {
            updateItem.setDesc(record.getDesc());
        }
        if (StringUtils.isNotEmpty(record.getImgUrl())) {
            updateItem.setImgUrl(record.getImgUrl());
        }
        //接下来是更新update时间
        updateItem.setUpdateTime(new Date());

         productDao.updateByPrimaryKeySelective(updateItem);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return productDao.deleteByPrimaryKey(id);
    }



}