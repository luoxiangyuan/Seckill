package com.luo.seckill.service;

import com.luo.seckill.bean.Order;
import com.luo.seckill.bean.OrderDetail;
import com.luo.seckill.bean.OrderDetailExample;
import com.luo.seckill.dao.OrderDetailMapper;
import com.luo.seckill.dao.OrderMapper;
import com.sun.corba.se.pept.transport.ReaderThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler;

import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderDetailMapper orderDetailMapper;

    //今日订单总数
    public int getTodayNum(){
        return orderMapper.getTodayNum();
    }

    //昨日订单总数
    public int getYesterdayNum(){
        return orderMapper.getYesterdayNum();
    }

    //今日销售额
    public Double getTodayAmount(){
        return orderMapper.getTodayAmount();
    }

    //未支付订单
    public int getUnpaidNum(int storeId){
        return orderMapper.getUnpaidNum(storeId);
    }

    //上月订单总数
    public int getPreMonth(int storeId){
        return orderMapper.getPreMonthNum(storeId);
    }

    //本月订单总数
    public int getThisMonth(int storeId){
        return orderMapper.getThisMonthNum(storeId);
    }

    //订单列表
    public List<Order> getList(int storeId){
        return orderMapper.selectByStoreId(storeId);
    }

    //获取订单详情
    public Map<String,Object> getOrderDetail(int id){
        List<Map<String, Object>> products = orderDetailMapper.getDetail(id);
        Map<String, Object> orderWithClient = orderMapper.getOrderWithClient(id);
        orderWithClient.put("products",products);
        return orderWithClient;
    }

    //删除订单
    public void delOrderDetail(int id){
        orderDetailMapper.deleteOrderDetail(id);
        orderMapper.deleteById(id);
    }

}
