package com.luo.seckill.service;

import com.luo.seckill.bean.Product;
import com.luo.seckill.dao.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductMapper productMapper;

    //获取所有商品总数
    public int getTotal(int storeId){
        return productMapper.getTotal(storeId);
    }

    //上架总数
    public int getShelvesNum(int storeId){
        return productMapper.getShelvesNum(storeId);
    }

    //下架总数
    public int getTakeOffNum(int storeId){
        return productMapper.getTakeOffNum(storeId);
    }

    //获取商品列表
    public List<Product> getProList(){
        return productMapper.selectByExample(null);
    }

    //更新商品
    public void updatePro(Product product){
        productMapper.updateByPrimaryKeySelective(product);
    }

    //根据id获取商品
    public Product getById(int id){
        return productMapper.selectByPrimaryKey(id);
    }

    //删除商品
    public void delPro(int id){
        productMapper.deleteByPrimaryKey(id);
    }

    //添加商品
    public void addPro(Product product){
        productMapper.insertSelective(product);
    }
}
