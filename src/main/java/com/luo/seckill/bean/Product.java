package com.luo.seckill.bean;

import java.util.Date;

public class Product {
    private Integer id;

    private String name;

    private String pic;

    private Integer publishStatus;

    private Integer saleNum;

    private Double price;

    private Double seckillPrice;

    private String description;

    private Integer stock;

    private Integer lowStock;

    private Date seckillStartTime;

    private Date seckillEndTime;

    private Integer seckillNumLimit;

    private Integer sellType;

    private Integer giftPoint;

    private Integer storeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public Integer getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(Integer publishStatus) {
        this.publishStatus = publishStatus;
    }

    public Integer getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSeckillPrice() {
        return seckillPrice;
    }

    public void setSeckillPrice(Double seckillPrice) {
        this.seckillPrice = seckillPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getLowStock() {
        return lowStock;
    }

    public void setLowStock(Integer lowStock) {
        this.lowStock = lowStock;
    }

    public Date getSeckillStartTime() {
        return seckillStartTime;
    }

    public void setSeckillStartTime(Date seckillStartTime) {
        this.seckillStartTime = seckillStartTime;
    }

    public Date getSeckillEndTime() {
        return seckillEndTime;
    }

    public void setSeckillEndTime(Date seckillEndTime) {
        this.seckillEndTime = seckillEndTime;
    }

    public Integer getSeckillNumLimit() {
        return seckillNumLimit;
    }

    public void setSeckillNumLimit(Integer seckillNumLimit) {
        this.seckillNumLimit = seckillNumLimit;
    }

    public Integer getSellType() {
        return sellType;
    }

    public void setSellType(Integer sellType) {
        this.sellType = sellType;
    }

    public Integer getGiftPoint() {
        return giftPoint;
    }

    public void setGiftPoint(Integer giftPoint) {
        this.giftPoint = giftPoint;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }
}