package com.luo.seckill.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.luo.seckill.bean.*;
import com.luo.seckill.service.OrderService;
import com.luo.seckill.service.ProductService;
import com.luo.seckill.service.UploadFilesService;
import com.luo.seckill.service.UserService;
import org.apache.catalina.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/admin")
@RestController
public class AdminController {
    @Autowired
    OrderService orderService;
    @Autowired
    ProductService productService;
    @Autowired
    UploadFilesService uploadFileService;
    @Autowired
    UserService userService;
    @Autowired
    Msg msg;

    //登录
    @PostMapping("/login")
    public Msg login(@RequestParam String username, @RequestParam String password,
                     HttpServletResponse response, HttpSession session){
        User user = userService.getUser(username);
        if (user == null || !user.getPassword().equals(password)){
            msg.setMsg("账号或密码错误");
            msg.setCode(100);
            return msg;
        }else {
            Store store = userService.getStore(username);
            session.setAttribute("storeId",store.getId());
        }
        return Msg.success(response);
    }

    //获取今日订单总数
    @GetMapping("/todayNum")
    public Msg getTodayNum(HttpServletResponse response) {
        Map<String, Integer> map = new HashMap<>();
        map.put("todayNum", orderService.getTodayNum());
        return Msg.success(response).add(map);
    }

    //获取昨日订单总数
    @GetMapping("/yesterdayNum")
    public Msg getYesterdayNum(HttpServletResponse response) {
        Map<String, Integer> map = new HashMap<>();
        map.put("yesterdayNum", orderService.getYesterdayNum());
        return Msg.success(response).add(map);
    }

    //今日销售总额
    @GetMapping("/todayAmount")
    public Msg getTodayAmount(HttpServletResponse response) {
        Map<String, Double> map = new HashMap<>();
        if (orderService.getTodayAmount() == null){
            map.put("todayAmount",0.0);
        }else {
            map.put("todayAmount", orderService.getTodayAmount());
        }
        return Msg.success(response).add(map);
    }

    //待付款订单
    @GetMapping("/unpaidNum")
    public Msg getUnpaidNum(HttpServletResponse response, HttpSession session) {
        int storeId = (int) session.getAttribute("storeId");
        Map<String, Integer> map = new HashMap<>();
        map.put("unpaidNum", orderService.getUnpaidNum(storeId));
        return Msg.success(response).add(map);
    }

    //获取商品总数
    @GetMapping("/proTotalNum")
    public Msg getProTotalNum(HttpSession session, HttpServletResponse response) {
        int storeId = (int) session.getAttribute("storeId");
        Map<String, Integer> map = new HashMap<>();
        map.put("total", productService.getTotal(storeId));
        return Msg.success(response).add(map);
    }

    //获取上架总数
    @GetMapping("/ShelvesNum")
    public Msg getShelvesNum(HttpServletResponse response, HttpSession session) {
        int storeId = (int) session.getAttribute("storeId");
        Map<String, Integer> map = new HashMap<>();
        map.put("ShelvesNum", productService.getShelvesNum(storeId));
        return Msg.success(response).add(map);
    }

    //获取下架总数
    @GetMapping("/TakeOffNum")
    public Msg getTakeOffNum(HttpServletResponse response, HttpSession session) {
        int storeId = (int) session.getAttribute("storeId");
        Map<String, Integer> map = new HashMap<>();
        map.put("TakeOffNum", productService.getTakeOffNum(storeId));
        return Msg.success(response).add(map);
    }

    //获取上月订单总数
    @GetMapping("/PreMonthOrderNum")
    public Msg getPreMonthOrderNum(HttpServletResponse response, HttpSession session) {
        int storeId = (int) session.getAttribute("storeId");
        Map<String, Integer> map = new HashMap<>();
        map.put("PreMonthOrderNum", orderService.getPreMonth(storeId));
        return Msg.success(response).add(map);
    }

    //获取本月订单总数
    @GetMapping("/ThisMonthOrderNum")
    public Msg getThisMonthOrderNum(HttpServletResponse response, HttpSession session) {
        int storeId = (int) session.getAttribute("storeId");
        Map<String, Integer> map = new HashMap<>();
        map.put("ThisMonthOrderNum", orderService.getThisMonth(storeId));
        return Msg.success(response).add(map);
    }

    //获取商品列表
    @GetMapping("/ProList")
    public Msg getProList(HttpServletResponse response, @RequestParam(defaultValue = "0") int page) {
        PageHelper.startPage(page, 5);
        List<Product> proList = productService.getProList();
        PageInfo<Product> productPageInfo = new PageInfo<>(proList);
        return Msg.success(response).add(productPageInfo.getList());
    }

    //更改商品的上架状态
    @PutMapping("/proPublish")
    public Msg putProPublish(@RequestParam int id, @RequestParam int publish_status, HttpServletResponse response) {
        Product product = productService.getById(id);
        if (product == null) {
            msg.setMsg("商品为空");
            msg.setCode(100);
            return msg;
        } else {
            product.setPublishStatus(publish_status);
            productService.updatePro(product);
        }
        return Msg.success(response);
    }

    //编辑商品
    @PutMapping("/product")
    public Msg updateProduct(@RequestParam int id, @RequestParam String name, @RequestParam double price,
                             @RequestParam String description,
                             @RequestParam int gift_point, @RequestParam(value = "pic") MultipartFile[] images,
                             HttpServletResponse response, HttpServletRequest request) {
        Product product = productService.getById(id);
        if (product == null) {
            msg.setMsg("商品为空");
            msg.setCode(100);
            return msg;
        } else {
            String imageURL = "";
            for (MultipartFile image : images) {
                imageURL += uploadFileService.upload(image, "/product/" + name + "/image", request) + ",";
            }
            imageURL = imageURL.substring(0, imageURL.length() - 1);
            product.setName(name);
            product.setPrice(price);
            product.setDescription(description);
            product.setGiftPoint(gift_point);
            product.setPic(imageURL);
            productService.updatePro(product);
        }
        return Msg.success(response);
    }

    //删除商品
    @DeleteMapping("/product")
    public Msg delPro(@RequestParam int id,HttpServletResponse response){
        productService.delPro(id);
        return Msg.success(response);
    }

    //新增商品
    @PostMapping("/product")
    public Msg addPro(@RequestParam String name, @RequestParam double price,
                      @RequestParam String description,
                      @RequestParam int gift_point, @RequestParam(value = "pic") MultipartFile[] images,
                      HttpServletResponse response, HttpServletRequest request,HttpSession session){
        String imageURL = "";
        for (MultipartFile image : images) {
            imageURL += uploadFileService.upload(image, "/product/" + name + "/image", request) + ",";
        }
        imageURL = imageURL.substring(0, imageURL.length() - 1);

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        product.setGiftPoint(gift_point);
        product.setPic(imageURL);
        product.setSellType(0);
        product.setPublishStatus(1);
        product.setStoreId((Integer) session.getAttribute("storeId"));

        productService.addPro(product);
        return Msg.success(response);
    }

    //订单列表
    @GetMapping("/orderList")
    public Msg getOrderList(@RequestParam(defaultValue = "0") int page, HttpServletResponse response,HttpSession session){
        int storeId = (int) session.getAttribute("storeId");
        PageHelper.startPage(page,10);
        List<Order> list = orderService.getList(storeId);
        PageInfo<Order> orderPageInfo = new PageInfo<>(list);
        return Msg.success(response).add(orderPageInfo.getList());
    }

    //订单详情
    @GetMapping("/orderDetail")
    public Msg getOrderDetail(@RequestParam int id,HttpServletResponse response){
        Map<String, Object> orderDetail = orderService.getOrderDetail(id);
        return Msg.success(response).add(orderDetail);
    }

    //删除订单
    @DeleteMapping("/order")
    public Msg delOrder(@RequestParam int id,HttpServletResponse response){
        orderService.delOrderDetail(id);
        return Msg.success(response);
    }
}
