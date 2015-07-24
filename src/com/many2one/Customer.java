package com.many2one;


import javax.persistence.*;

/**
 * Created by s016374 on 15/7/24.
 */

@Entity
@Table(name = "CUSTOMERS")
public class Customer {
    @Id
    @GeneratedValue
    @Column(name = "CUSTOMER_ID")
    private Integer customerId;
    @Column(name = "CUSTOMER_NAME")
    private String customerName;

    public Customer(String customerName) {
        this.customerName = customerName;
    }

    public Customer() {
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
