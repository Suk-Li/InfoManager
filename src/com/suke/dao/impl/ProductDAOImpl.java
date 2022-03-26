package com.suke.dao.impl;


import com.suke.dao.IProductDAO;
import com.suke.entity.Product;
import com.suke.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProductDAOImpl implements IProductDAO {
    /*
     * 获取某页所有产品信息
     * */
    @Override
    public ArrayList<Product> findProduct(int startindex, int recordnum) throws Exception {
        //连接对象
        Connection connection;
        //发送sql语句对象
        PreparedStatement statement = null;
        ArrayList<Product> productList = new ArrayList<>();
        try {
            connection = JDBCUtils.getConnection();
            //获取Statement对象
            statement = connection.prepareStatement("SELECT * FROM product limit ?,?");
            //添加参数
            statement.setInt(1, startindex);
            statement.setInt(2, recordnum);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setPid(rs.getInt("pid"));
                product.setName(rs.getString("name"));
                product.setNote(rs.getString("note"));
                product.setPrice(rs.getFloat("price"));
                product.setAmount(rs.getInt("amount"));
                product.setPic(rs.getString("pic"));
                productList.add(product);
            }
            return productList;
        } catch (Exception e) {
            e.printStackTrace();
            return productList;
        } finally {
            JDBCUtils.close(null, statement, null);
        }
    }

    /*
     * 根据产品名称获取某页产品信息
     * */
    @Override
    public ArrayList<Product> findProduct(String name, int startindex, int recordnum) throws Exception {
        //连接对象
        Connection connection;
        //发送sql语句对象
        PreparedStatement statement = null;
        ArrayList<Product> productList = new ArrayList<>();
        try {
            connection = JDBCUtils.getConnection();
            //获取Statement对象
            statement = connection.prepareStatement("SELECT * FROM product where name like \"%\"?\"%\" or note like \"%\"?\"%\" limit ?,?");
            //添加参数
            statement.setString(1, name);
            statement.setString(2, name);
            statement.setInt(3, startindex);
            statement.setInt(4, recordnum);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setPid(rs.getInt("pid"));
                product.setName(rs.getString("name"));
                product.setNote(rs.getString("note"));
                product.setPrice(rs.getFloat("price"));
                product.setAmount(rs.getInt("amount"));
                product.setPic(rs.getString("pic"));
                productList.add(product);
            }
            return productList;
        } catch (Exception e) {
            e.printStackTrace();
            return productList;
        } finally {
            JDBCUtils.close(null, statement, null);
        }
    }

    /*
     * 根据产品id获取产品信息
     * */
    @Override
    public Product findProductByID(int pid) throws Exception {
        //连接对象
        Connection connection;
        //发送sql语句对象
        PreparedStatement statement = null;
        Product product = null;
        try {
            connection = JDBCUtils.getConnection();
            //获取Statement对象
            statement = connection.prepareStatement("SELECT * FROM product where pid = ?");
            //添加参数
            statement.setInt(1, pid);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                product = new Product();
                product.setPid(rs.getInt("pid"));
                product.setName(rs.getString("name"));
                product.setNote(rs.getString("note"));
                product.setPrice(rs.getFloat("price"));
                product.setAmount(rs.getInt("amount"));
                product.setPic(rs.getString("pic"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return product;
        } finally {
            JDBCUtils.close(null, statement, null);
        }
        return product;
    }

    /*
     * 获取所有产品信息的数目(分页用)
     * */
    @Override
    public int getCount() throws Exception {
        //连接对象
        Connection connection;
        //发送sql语句对象
        PreparedStatement statement = null;
        int count = 0;
        try {
            connection = JDBCUtils.getConnection();
            //获取Statement对象
            statement = connection.prepareStatement("SELECT count(*) FROM product");
            //添加参数
            ResultSet rs = statement.executeQuery();
            rs.next();
            count = rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
            return count;
        } finally {
            JDBCUtils.close(null, statement, null);
        }
        return count;
    }

    /*
     * 根据产品名称获取产品信息的数目(分页用)
     * */
    @Override
    public int getCount(String name) throws Exception {
        //连接对象
        Connection connection;
        //发送sql语句对象
        PreparedStatement statement = null;
        int count = 0;
        try {
            connection = JDBCUtils.getConnection();
            //获取Statement对象
            statement = connection.prepareStatement("SELECT count(*) FROM product where name like \"%\"?\"%\" or note like \"%\"?\"%\"");
            //添加参数
            statement.setString(1, name);
            statement.setString(2, name);
            ResultSet rs = statement.executeQuery();
            rs.next();
            count = rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
            return count;
        } finally {
            JDBCUtils.close(null, statement, null);
        }
        return count;
    }

    /*
     * 新增产品信息
     * */
    @Override
    public int addProduct(Product p) throws Exception {
        //连接对象
        Connection connection;
        //发送sql语句对象
        PreparedStatement statement = null;
        int val = 0;
        try {
            connection = JDBCUtils.getConnection();
            //获取Statement对象
            statement = connection.prepareStatement("insert into product(name,note,price,amount,pic) values(?,?,?,?,?)");
            //添加参数
            statement.setString(1, p.getName());
            statement.setString(2, p.getNote());
            statement.setInt(4, p.getAmount());
            statement.setFloat(3, p.getPrice());
            statement.setString(5, p.getPic());
            val = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return val;
        } finally {
            JDBCUtils.close(null, statement, null);
        }
        return val;
    }

    /*
     * 修改产品信息
     * */
    @Override
    public int updateProduct(Product p) throws Exception {
        //连接对象
        Connection connection;
        //发送sql语句对象
        PreparedStatement statement = null;
        int val = 0;
        try {
            connection = JDBCUtils.getConnection();
            //获取Statement对象
            statement = connection.prepareStatement("update product set name=?,note=?,amount=?,price=?,pic=? where pid=?");
            //添加参数
            statement.setString(1, p.getName());
            statement.setString(2, p.getNote());
            statement.setInt(3, p.getAmount());
            statement.setFloat(4, p.getPrice());
            statement.setString(5, p.getPic());
            statement.setInt(6, p.getPid());
            val = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return val;
        } finally {
            JDBCUtils.close(null, statement, null);
        }
        return val;
    }

    /*
     * 删除产品信息
     * */
    @Override
    public int deleteProduct(int pid) throws Exception {
        //连接对象
        Connection connection;
        //发送sql语句对象
        PreparedStatement statement = null;
        int val = 0;
        try {
            connection = JDBCUtils.getConnection();
            //获取Statement对象
            statement = connection.prepareStatement("delete from product where pid=?");
            //添加参数
            statement.setInt(1, pid);
            val = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return val;
        } finally {
            JDBCUtils.close(null, statement, null);
        }
        return val;
    }
}