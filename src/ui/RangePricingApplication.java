/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.util.ArrayList;
import java.util.List;

import model.Business.Business;
import model.Business.ConfigureABusiness;
import model.CustomerManagement.CustomerDirectory;
import model.CustomerManagement.CustomerProfile;
import model.CustomerManagement.CustomerSummary;
import model.CustomerManagement.CustomersReport;
import model.ProductManagement.ProductCatalog;
import model.ProductManagement.ProductsReport;
import model.Supplier.Supplier;
import model.Supplier.SupplierDirectory;
import model.OrderManagement.Order;

/**
 *
 * @author kal bugrara
 */
public class RangePricingApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        // Populate the model with the following:
        // a. Business - 1.
        // b. Suppliers - 30.
        // c. Pick any 10 Suppliers and add 20 Products to each.
        // d. Customers - 50.
        // e. Pick any 25 Customers and add 1-3 Orders with 1-10 Items to each.
        Business business = ConfigureABusiness.createABusinessWithDetails("Hello World Specialist", 30, 10, 20, 50, 25,
                3, 10);
        // business.printShortInfo();

        // - Pick three random Customers and print out their Sales orders
        System.out.println("------------------------------------------------------------");
        System.out.println("Pick three random Customers and print out their Sales orders: \n");
        CustomerDirectory cd = business.getCustomerDirectory();

        for (int i = 0; i < 3; i++) {
            CustomerProfile randomCustomer = cd.pickRandomCustomer();
            randomCustomer.printOrders();
        }

        System.out.println("------------------------------------------------------------");
        // - Pick three random Suppliers and find their most expensive products.
        System.out.println("Pick three random Suppliers and find their most expensive products: \n");
        SupplierDirectory sd = business.getSupplierDirectory();
        for (int i = 0; i < 3; i++) {
            Supplier randomSupplier = sd.pickRandomSupplierWithProduct();
            System.out.println("The most expensive product in " + randomSupplier + " is: "
                    + randomSupplier.getMostExpensiveProduct());
        }

        System.out.println("------------------------------------------------------------");
        // - Find a customer who spend most money with us.
        System.out.println("Find a customer who spend most money with us \n");
        CustomersReport report = cd.generateCustomerPerformanceReport();
        CustomerSummary max = report.getSpentMostCustomer();
        CustomerProfile customer = max.getCustomer();
        System.out.println("The customer who spend most money with us is: " + customer.getCustomerId()
                + ", the total spending is: " + max.getCustomerTotal());
        // - Find a Supplier with most sales.
        // - Find a Supplier with least sales (do not include Supplier with zero sales).
    }

}
