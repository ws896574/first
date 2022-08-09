package com.bimowo.first.dao;

import com.bimowo.first.model.Product;
import com.bimowo.first.utils.CommonQueryBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository //采用这个注解来声明dao
public interface ProductDao {
    /**
     *
     * 查询（根据主键ID查询）
     *
     **/
    Product selectByPrimaryKey (@Param("id") Long id );
    /**
     *
     * 添加
     *
     **/
    int insert( Product record );
    /**
     *
     * list查询
     *
     **/
    List<Product> list (Product record);
    /**
     *
     * list分页查询
     *
     **/
    List<Product> list4Page ( Product record,  @Param("commonQueryParam") CommonQueryBean query);

    long count(Product record);

    /**
     *
     * 修改 （匹配有值的字段）
     *
     **/
    int updateByPrimaryKeySelective( Product record );

    /**
     *
     * 删除（根据主键ID删除）
     *
     **/
    int deleteByPrimaryKey ( @Param("id") Long id );
}


