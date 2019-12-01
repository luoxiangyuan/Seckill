package com.luo.seckill.dao;

import com.luo.seckill.bean.OrderDetail;
import com.luo.seckill.bean.OrderDetailExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface OrderDetailMapper {
    long countByExample(OrderDetailExample example);

    int deleteByExample(OrderDetailExample example);

    void deleteOrderDetail(int id);

    int insert(OrderDetail record);

    List<Map<String,Object>> getDetail(int id);

    int insertSelective(OrderDetail record);

    List<OrderDetail> selectByExample(OrderDetailExample example);

    int updateByExampleSelective(@Param("record") OrderDetail record, @Param("example") OrderDetailExample example);

    int updateByExample(@Param("record") OrderDetail record, @Param("example") OrderDetailExample example);
}