/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Business;

import java.util.Random;

import model.Business.Business;
import model.CustomerManagement.CustomerDirectory;
import model.CustomerManagement.CustomerProfile;
import model.MarketingManagement.MarketingPersonDirectory;
import model.MarketingManagement.MarketingPersonProfile;
import model.OrderManagement.MasterOrderList;
import model.OrderManagement.Order;
import model.OrderManagement.OrderItem;
import model.Personnel.EmployeeDirectory;
import model.Personnel.EmployeeProfile;
import model.Personnel.Person;
import model.Personnel.PersonDirectory;
import model.ProductManagement.Product;
import model.ProductManagement.ProductCatalog;
import model.SalesManagement.SalesPersonDirectory;
import model.SalesManagement.SalesPersonProfile;
import model.Supplier.Supplier;
import model.Supplier.SupplierDirectory;
import model.UserAccountManagement.UserAccount;
import model.UserAccountManagement.UserAccountDirectory;

/**
 *
 * @author kal bugrara
 */
public class ConfigureABusiness {

    static int upperPriceLimit = 50;
    static int lowerPriceLimit = 10;
    static int range = 5;
    static int productMaxQuantity = 5;

    public static Business createABusinessWithDetails(String name, int supplierCount, int suppliersWithProduct,
            int productCount, int customerCount, int customersWithOrders, int orderCount, int itemCount) {
        /*
         * Create a business with all details provided and return the business
         * ----------------------------------------------------
         * Parameters:
         * - name: name of the business
         * - supplierCount: how many suppliers are in the business
         * - supplierWithProduct: how many suppliers have products
         * - productCount: how many product do each supplier has
         * - customerCount: how many customers does the business owns
         * - orderCount: how many customers does the business owns
         * - itemCount: maximum count of items contained in an order
         * ----------------------------------------------------
         * Return:
         * business that was created with above info
         */
        Business business = new Business(name);

        // Add suppliers
        loadSuppliers(business, supplierCount);

        // Add productCount products to supplierWithProduct suppliers
        loadProductsToNSuppliers(business, suppliersWithProduct, productCount);

        // Add Customers
        loadCustomers(business, customerCount);

        // Pick customerWithOrder customer and give add 1 - orderCount order to each,
        // each order contains 1 - itemCount items
        loadOrdersToNCustomers(business, customersWithOrders, orderCount, itemCount);

        return business;
    }

    public static Business createABusinessAndLoadALotOfData(String name, int supplierCount, int productCount,
            int customerCount, int orderCount, int itemCount) {
        Business business = new Business(name);

        // Add Suppliers +
        loadSuppliers(business, supplierCount);

        // Add Products +
        loadProducts(business, productCount);

        // Add Customers
        loadCustomers(business, customerCount);

        // Add Order
        loadOrders(business, orderCount, itemCount);

        return business;
    }

    public static void loadSuppliers(Business b, int supplierCount) {
        // create supplierCount suppliers in business b
        SupplierDirectory supplierDirectory = b.getSupplierDirectory();
        for (int index = 1; index <= supplierCount; index++) {
            supplierDirectory.newSupplier("Supplier #" + index);
        }
    }

    static void loadProducts(Business b, int productCount) {
        // create 1 to productCount products for every suppliers in business b
        SupplierDirectory supplierDirectory = b.getSupplierDirectory();

        for (Supplier supplier : supplierDirectory.getSuplierList()) {

            int randomProductNumber = getRandom(1, productCount);
            ProductCatalog productCatalog = supplier.getProductCatalog();

            for (int index = 1; index <= randomProductNumber; index++) {

                String productName = "Product #" + index + " from " + supplier.getName();
                int randomFloor = getRandom(lowerPriceLimit, lowerPriceLimit + range);
                int randomCeiling = getRandom(upperPriceLimit - range, upperPriceLimit);
                int randomTarget = getRandom(randomFloor, randomCeiling);

                productCatalog.newProduct(productName, randomFloor, randomCeiling, randomTarget);
            }
        }
    }

    static void loadProductsToNSuppliers(Business b, int n, int productCount) {
        SupplierDirectory supplierDirectory = b.getSupplierDirectory();

        for (int i = 0; i < 10; i++) {
            Supplier randomSupplier = supplierDirectory.pickRandomSupplier();
            ProductCatalog productCatalog = randomSupplier.getProductCatalog();

            for (int index = 1; index <= productCount; index++) {
                String productName = "Product #" + index + " from " + randomSupplier.getName();
                int randomFloor = getRandom(lowerPriceLimit, lowerPriceLimit + range);
                int randomCeiling = getRandom(upperPriceLimit - range, upperPriceLimit);
                int randomTarget = getRandom(randomFloor, randomCeiling);

                productCatalog.newProduct(productName, randomFloor, randomCeiling, randomTarget);
            }
        }
    }

    static int getRandom(int lower, int upper) {
        Random r = new Random();

        // nextInt(n) will return a number from zero to 'n'. Therefore e.g. if I want
        // numbers from 10 to 15
        // I will have result = 10 + nextInt(5)
        int randomInt = lower + r.nextInt(upper - lower);
        return randomInt;
    }

    static void loadCustomers(Business b, int customerCount) {
        // create customerCount of CustomerProfile in b business b
        CustomerDirectory customerDirectory = b.getCustomerDirectory();
        PersonDirectory personDirectory = b.getPersonDirectory();

        for (int index = 1; index <= customerCount; index++) {
            Person newPerson = personDirectory.newPerson("" + index);
            customerDirectory.newCustomerProfile(newPerson);
        }
    }

    static void loadOrders(Business b, int orderCount, int itemCount) {

        // reach out to masterOrderList
        MasterOrderList mol = b.getMasterOrderList();

        // pick a random customer (reach to customer directory)
        CustomerDirectory cd = b.getCustomerDirectory();
        SupplierDirectory sd = b.getSupplierDirectory();

        for (int index = 0; index < orderCount; index++) {
            CustomerProfile randomCustomer = cd.pickRandomCustomer();
            if (randomCustomer == null) {
                System.out.println("Cannot generate orders. No customers in the customer directory.");
                return;
            }

            // create an order for that customer
            Order randomOrder = mol.newOrder(randomCustomer);

            // add order items
            // -- pick a supplier first (randomly)
            // -- pick a product (randomly)
            // -- actual price, quantity

            int randomItemCount = getRandom(1, itemCount);
            for (int itemIndex = 0; itemIndex < randomItemCount; itemIndex++) {

                Supplier randomSupplier = sd.pickRandomSupplier();
                if (randomSupplier == null) {
                    System.out.println("Cannot generate orders. No supplier in the supplier directory.");
                    return;
                }
                ProductCatalog pc = randomSupplier.getProductCatalog();
                Product randomProduct = pc.pickRandomProduct();
                if (randomProduct == null) {
                    System.out.println("Cannot generate orders. No products in the product catalog.");
                    return;
                }

                int randomPrice = getRandom(randomProduct.getFloorPrice(), randomProduct.getCeilingPrice());
                int randomQuantity = getRandom(1, productMaxQuantity);

                OrderItem oi = randomOrder.newOrderItem(randomProduct, randomPrice, randomQuantity);
                // Make sure order items are connected to the order
            }
        }
    }

    public static void loadOrdersToNCustomers(Business b, int n, int orderCount, int itemCount) {
        // reach out to masterOrderList
        MasterOrderList mol = b.getMasterOrderList();

        // pick a random customer (reach to customer directory)
        CustomerDirectory cd = b.getCustomerDirectory();
        SupplierDirectory sd = b.getSupplierDirectory();

        for (int i = 0; i < n; i++) {
            CustomerProfile randomCustomer = cd.pickRandomCustomer();

            int randomOrderCount = getRandom(1, orderCount);
            for (int orderIndex = 0; orderIndex < randomOrderCount; orderIndex++) {
                // create an order for that customer
                Order randomOrder = mol.newOrder(randomCustomer);

                // add order items
                // -- pick a supplier first (randomly)
                // -- pick a product (randomly)
                // -- actual price, quantity
                int randomItemCount = getRandom(1, itemCount);
                for (int itemIndex = 0; itemIndex < randomItemCount; itemIndex++) {
                    Supplier randomSupplier = sd.pickRandomSupplierWithProduct();
                    if (randomSupplier == null) {
                        System.out.println("Cannot generate orders. No supplier has any products.");
                        return;
                    }
                    ProductCatalog pc = randomSupplier.getProductCatalog();
                    Product randomProduct = pc.pickRandomProduct();
                    if (randomProduct == null) {
                        System.out.println("Cannot generate orders. No products in the product catalog.");
                        return;
                    }

                    int randomPrice = getRandom(randomProduct.getFloorPrice(), randomProduct.getCeilingPrice());
                    int randomQuantity = getRandom(1, productMaxQuantity);

                    OrderItem oi = randomOrder.newOrderItem(randomProduct, randomPrice, randomQuantity);
                    randomProduct.addOrderItem(oi);
                }
            }
        }
    }

}
