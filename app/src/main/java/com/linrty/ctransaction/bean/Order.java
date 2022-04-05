package com.linrty.ctransaction.bean;

import com.google.gson.annotations.SerializedName;

/**
  * @ClassName:      Order
  * @Description:    订单基类，包含订单状态：交易成功、待作者确认、交易失败，作品ID,作品标题，购买者给出的价格，订单创建时间，交易成功时间，交易失败时间，作品创建时间
  * @Author:         Linrty
  * @CreateDate:     2022/4/5
  * @UpdateUser:     updater
  * @UpdateDate:     2022/4/5
  * @UpdateRemark:   更新内容
  * @Version:        1.0
  */

public class Order {

     /**
      * 订单ID
      */
    @SerializedName(value = "order_id")
    private Long orderId;

     /**
      * 作品ID，一个订单对应一个作品
      */
    @SerializedName(value = "work_id")
    private Long workId;

     /**
      * 订单状态：交易成功、待作者确认、交易失败
      * 1：交易成功,对应的静态常量CODE_ORDER_SUCCESS
      * -1：交易失败,对应的静态常量CODE_ORDER_FAIL
      * 0：待作者确认,对应的静态常量CODE_ORDER_PROCESS
      */
    @SerializedName(value = "state")
    private int state;

     /**
      * 作品标题，为了省去联表查询，嘿嘿
      */
    @SerializedName(value = "work_title")
    private String workTitle;

     /**
      * 购买者给出的价格
      */
    @SerializedName(value = "price")
    private double price;

     /**
      * 订单创建时间 YYYY-MM-DD HH:mm:ss
      */
    @SerializedName(value = "create_time")
    private String createTime;

     /**
      * 该字段只有在订单状态为成功时才有效
      * 订单交易成功 YYYY-MM-DD HH:mm:ss
      */
    @SerializedName(value = "success_time")
    private String successTime;

     /**
      * 该字段只有在订单状态为失败时才有效，如果作者三天内都没有确认的话，默认订单交易失败，将购买者的money加回去
      * 订单交易失败 YYYY-MM-DD HH:mm:ss
      */
    @SerializedName(value = "fail_time")
    private String failTime;

     /**
      * 作品创建时间，为了省去联表的事
      * YYYY-MM-DD HH:mm:ss
      */
     @SerializedName(value = "work_create_time")
     private String workCreateTime;





     public Long getOrderId() {
         return orderId;
     }

     /**
      * 不重构构造函数，使用链式构造更方便
      * @param orderId
      * @return
      */
     public Order setOrderId(Long orderId) {
         this.orderId = orderId;
         return this;
     }

     public Long getWorkId() {
         return workId;
     }

     public Order setWorkId(Long workId) {
         this.workId = workId;
         return this;
     }

     public int getState() {
         return state;
     }

     public Order setState(int state) {
         this.state = state;
         return this;
     }

     public String getWorkTitle() {
         return workTitle;
     }

     public Order setWorkTitle(String workTitle) {
         this.workTitle = workTitle;
         return this;
     }

     public double getPrice() {
         return price;
     }

     public Order setPrice(double price) {
         this.price = price;
         return this;
     }

     public String getCreateTime() {
         return createTime;
     }

     public Order setCreateTime(String createTime) {
         this.createTime = createTime;
         return this;
     }

     public String getSuccessTime() {
         return successTime;
     }

     public Order setSuccessTime(String successTime) {
         this.successTime = successTime;
         return this;
     }

     public String getFailTime() {
         return failTime;
     }

     public Order setFailTime(String failTime) {
         this.failTime = failTime;
         return this;
     }

     public String getWorkCreateTime() {
         return workCreateTime;
     }

     public Order setWorkCreateTime(String workCreateTime) {
         this.workCreateTime = workCreateTime;
         return this;
     }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Order order = (Order) o;

        if (state != order.state) {
            return false;
        }
        if (Double.compare(order.price, price) != 0) {
            return false;
        }
        if (orderId != null ? !orderId.equals(order.orderId) : order.orderId != null) {
            return false;
        }
        if (workId != null ? !workId.equals(order.workId) : order.workId != null) {
            return false;
        }
        if (workTitle != null ? !workTitle.equals(order.workTitle) : order.workTitle != null) {
            return false;
        }
        if (createTime != null ? !createTime.equals(order.createTime) : order.createTime != null) {
            return false;
        }
        if (successTime != null ? !successTime.equals(order.successTime) : order.successTime != null) {
            return false;
        }
        if (failTime != null ? !failTime.equals(order.failTime) : order.failTime != null) {
            return false;
        }
        return workCreateTime != null ? workCreateTime.equals(order.workCreateTime) : order.workCreateTime == null;
    }


    @Override
    public int hashCode() {
        int result;
        long temp;
        result = orderId != null ? orderId.hashCode() : 0;
        result = 31 * result + (workId != null ? workId.hashCode() : 0);
        result = 31 * result + state;
        result = 31 * result + (workTitle != null ? workTitle.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (successTime != null ? successTime.hashCode() : 0);
        result = 31 * result + (failTime != null ? failTime.hashCode() : 0);
        result = 31 * result + (workCreateTime != null ? workCreateTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", workId=" + workId +
                ", state=" + state +
                ", workTitle='" + workTitle + '\'' +
                ", price=" + price +
                ", createTime='" + createTime + '\'' +
                ", successTime='" + successTime + '\'' +
                ", failTime='" + failTime + '\'' +
                ", workCreateTime='" + workCreateTime + '\'' +
                '}';
    }
}
