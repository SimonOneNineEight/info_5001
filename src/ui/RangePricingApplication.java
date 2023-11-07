/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import model.Business.Business;
import model.Business.ConfigureABusiness;
import model.CustomerManagement.CustomerDirectory;
import model.CustomerManagement.CustomerProfile;
import model.Supplier.Supplier;
import model.Supplier.SupplierDirectory;

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
        CustomerProfile customer = cd.getSpendMosCustomerProfile();
        System.out.println("The customer who spend most money with us is: " + customer.getCustomerId()
                + ", the total spending is: " + customer.getTotalPrice());
        System.out.println("------------------------------------------------------------");
        // - Find a Supplier with most sales.
        System.out.println("Find a Supplier with most sales: ");
        Supplier bestSupplier = sd.getBestSalesSupplier();
        System.out.println("The supplier that has most sales is: " + bestSupplier.getName()
                + ", and the total sales is: " + bestSupplier.getTotalSalesIncome());
        System.out.println("------------------------------------------------------------");
        // - Find a Supplier with least sales (do not include Supplier with zero sales).
        System.out.println("Find a Supplier with least sales (do not include Supplier with zero sales): ");
        Supplier worstSupplier = sd.getWorstSalesSupplier();
        System.out.println("The supplier that has least sales is: " + worstSupplier.getName()
                + ", and the total sales is: " + worstSupplier.getTotalSalesIncome());
    }

}
