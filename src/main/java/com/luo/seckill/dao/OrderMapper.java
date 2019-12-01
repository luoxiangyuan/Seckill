package com.luo.seckill.dao;

import com.luo.seckill.bean.Order;
import com.luo.seckill.bean.OrderExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface OrderMapper {
    long countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    int getTodayNum();

    int getYesterdayNum();

    int getUnpaidNum(int storeId);

    int getThisMonthNum(int storeId);

    int getPreMonthNum(int storeId);

    Map<String,Object> getOrderWithClient(int id);

    void deleteById(int id);

    Double getTodayAmount();

    List<Order> selectByExample(OrderExample example);

    List<Order> selectByStoreId(int storeId);

    Order selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}