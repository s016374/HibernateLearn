package com.many2one;

import javax.persistence.*;

/**
 * Created by s016374 on 15/7/24.
 */
@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Integer orderId;
    @Column(name = "ORDER_NAME")
    private String orderName;
    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID",referencedColumnName = "CUSTOMER_ID")
    private Customer customer;

    public Order() {
    }

    public Order(Customer customer) {
        this.customer = customer;
    }

    public Order(String orderName) {
        this.orderName = orderName;
    }

    public Order(String orderName, Customer customer) {
        this.orderName = orderName;
        this.customer = customer;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
