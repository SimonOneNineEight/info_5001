/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.CustomerManagement;

import java.util.ArrayList;

/**
 *
 * @author kal bugrara
 */
public class CustomersReport {
    ArrayList<CustomerSummary> customerlist;

    public CustomersReport() {
        this.customerlist = new ArrayList<>();
    }

    public void addCustomerSummary(CustomerSummary cs) {
        this.customerlist.add(cs);
    }

    public ArrayList<CustomerSummary> getCustomerSummaries() {
        return this.customerlist;
    }

    public CustomerSummary getSpentMostCustomer() {
        CustomerSummary max = this.customerlist.get(0);

        for (CustomerSummary summary : this.customerlist) {
            if (summary.getCustomerTotal() > max.getCustomerTotal()) {
                max = summary;
            }
        }

        return max;
    }

}
