package com.suke.service.impl;

import com.suke.dao.IProductDAO;
import com.suke.dao.impl.ProductDAOImpl;
import com.suke.entity.Product;
import com.suke.service.ProductService;
import com.suke.utils.JDBCUtils;

import java.sql.Connection;
import java.util.ArrayList;

public class ProductServiceImpl implements ProductService {

    private IProductDAO productDAO = new ProductDAOImpl();

    /*
     * 获取某页所有产品信息
     * */
    @Override
    public ArrayList<Product> findProduct(int startindex, int recordnum) throws Exception {
        //连接对象
        Connection connection = null;
        ArrayList<Product> productList = new ArrayList<>();
        try {
            //获取数据库连接
            connection = JDBCUtils.getConnection();
            //获取查找的产品信息
            productList = productDAO.findProduct(startindex, recordnum);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, null);
        }
        return productList;
    }

    /*
    * 根据产品名称获取某页产品信息
    * */
    @Override
    public ArrayList<Product> findProduct(String name, int startindex, int recordnum) throws Exception {
        //连接对象
        Connection connection = null;
        ArrayList<Product> productList = new ArrayList<>();
        try {
            //获取数据库连接
            connection = JDBCUtils.getConnection();
            //获取查找的产品信息
            productList = this.productDAO.findProduct(name, startindex, recordnum);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, null);
        }
        return productList;
    }

    /*
    * 根据产品id获取产品信息
    * */
    @Override
    public Product findProductByID(int pid) throws Exception {
        //连接对象
        Connection connection = null;
        Product product = null;
        try {
            //获取数据库连接
            connection = JDBCUtils.getConnection();
            //获取查找的产品信息
            product = productDAO.findProductByID(pid);
        } catch (Exception e) {
            e.printStackTrace();
            return product;
        } finally {
            JDBCUtils.close(connection, null);
        }
        return product;
    }

    /*
    * 获取所有产品信息的数目(分页用)
    * */
    @Override
    public int getCount() throws Exception {
        //连接对象
        Connection connection = null;
        int count = 0;
        try {
            //获取数据库连接
            connection = JDBCUtils.getConnection();
            //获取所有产品信息的数目
            count = productDAO.getCount() ;
        } catch (Exception e) {
            e.printStackTrace();
            return count;
        } finally {
            JDBCUtils.close(connection, null);
        }
        return count;
    }

    /*
    * 根据产品名称获取产品信息的数目(分页用)
    * */
    @Override
    public int getCount(String name) throws Exception {
        //连接对象
        Connection connection = null;
        int count = 0;
        try {
            //获取数据库连接
            connection = JDBCUtils.getConnection();
            //获取所有产品信息的数目
            count = productDAO.getCount(name) ;
        } catch (Exception e) {
            e.printStackTrace();
            return count;
        } finally {
            JDBCUtils.close(connection, null);
        }
        return count;
    }

    /*
    * 新增产品信息
    * */
    @Override
    public int addProduct(Product p) throws Exception {
        //连接对象
        Connection connection = null;
        int ret = 0;
        try {
            //获取数据库连接
            connection = JDBCUtils.getConnection();
            //获取新增产品信息
            ret = productDAO.addProduct(p)  ;
        } catch (Exception e) {
            e.printStackTrace();
            return ret;
        } finally {
            JDBCUtils.close(connection, null);
        }
        return ret;
    }

    /*
    * 修改产品信息
    * */
    @Override
    public int updateProduct(Product p) throws Exception {
        //连接对象
        Connection connection = null;
        int ret = 0;
        try {
            //获取数据库连接
            connection = JDBCUtils.getConnection();
            //获取修改产品信息
            ret = productDAO.updateProduct(p) ;
        } catch (Exception e) {
            e.printStackTrace();
            return ret;
        } finally {
            JDBCUtils.close(connection, null);
        }
        return ret;
    }

    /*
    * 删除产品信息
    * */
    @Override
    public int deleteProduct(int pid) throws Exception {
        //连接对象
        Connection connection = null;
        int ret = 0;
        try {
            //获取数据库连接
            connection = JDBCUtils.getConnection();
            //获取删除产品信息
            ret = productDAO.deleteProduct(pid) ;
        } catch (Exception e) {
            e.printStackTrace();
            return ret;
        } finally {
            JDBCUtils.close(connection, null);
        }
        return ret;
    }
}
