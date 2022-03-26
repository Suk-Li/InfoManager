package com.suke.service;


import com.suke.entity.Product;

import java.util.ArrayList;

public interface ProductService {
    //获取某页所有产品信息
    ArrayList<Product> findProduct(int startindex, int recordnum) throws Exception;

    //根据产品名称获取某页产品信息
    ArrayList<Product> findProduct(String name, int startindex, int recordnum) throws Exception;

    //根据产品id获取产品信息
    Product findProductByID(int pid) throws Exception;

    //获取所有产品信息的数目(分页用)
    int getCount() throws Exception;

    //根据产品名称获取产品信息的数目(分页用)
    int getCount(String name) throws Exception;

    //新增产品信息
    int addProduct(Product p) throws Exception;

    //修改产品信息
    int updateProduct(Product p) throws Exception;

    //删除产品信息
    int deleteProduct(int pid) throws Exception;
}
