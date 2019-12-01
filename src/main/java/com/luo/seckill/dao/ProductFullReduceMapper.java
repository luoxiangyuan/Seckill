package com.luo.seckill.dao;

import com.luo.seckill.bean.ProductFullReduce;
import com.luo.seckill.bean.ProductFullReduceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductFullReduceMapper {
    long countByExample(ProductFullReduceExample example);

    int deleteByExample(ProductFullReduceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductFullReduce record);

    int insertSelective(ProductFullReduce record);

    List<ProductFullReduce> selectByExample(ProductFullReduceExample example);

    ProductFullReduce selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductFullReduce record, @Param("example") ProductFullReduceExample example);

    int updateByExample(@Param("record") ProductFullReduce record, @Param("example") ProductFullReduceExample example);

    int updateByPrimaryKeySelective(ProductFullReduce record);

    int updateByPrimaryKey(ProductFullReduce record);
}