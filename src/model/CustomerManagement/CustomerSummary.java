/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.CustomerManagement;

import java.util.ArrayList;

import model.OrderManagement.Order;

/**
 *
 * @author kal bugrara
 */
public class CustomerSummary {
    private CustomerProfile customer;
    private ArrayList<Order> subjectOrders;
    private int customerTotal;

    public CustomerSummary(CustomerProfile cp) {
        this.customer = cp;
        this.subjectOrders = cp.getOrders();
    }

    public void addOrder(Order order) {
        this.subjectOrders.add(order);
        this.customerTotal += order.getOrderTotal();
    }

    public int getCustomerTotal() {
        return this.customerTotal;
    }

    public CustomerProfile getCustomer() {
        return this.customer;
    }

}
