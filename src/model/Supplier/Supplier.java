/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Supplier;

import java.util.ArrayList;

import model.ProductManagement.Product;
import model.ProductManagement.ProductCatalog;
import model.ProductManagement.ProductSummary;
import model.ProductManagement.ProductsReport;

/**
 *
 * @author kal bugrara
 */
public class Supplier {
    String name;
    ProductCatalog productcatalog;
    ProductsReport productsreport;

    public Supplier(String n) {
        name = n;
        productcatalog = new ProductCatalog("software");

    }

    public ProductsReport prepareProductsReport() {
        productsreport = productcatalog.generatProductPerformanceReport();
        return productsreport;
    }

    public ArrayList<ProductSummary> getProductsAlwaysAboveTarget() {
        if (productsreport == null)
            productsreport = prepareProductsReport();
        return productsreport.getProductsAlwaysAboveTarget();

    }

    public String getName() {
        return name;
    }

    public ProductCatalog getProductCatalog() {
        return productcatalog;
    }

    public void printShortInfo() {
        System.out.println("Checking supplier " + name);
        productcatalog.printShortInfo();
    }

    public ArrayList<Product> getProductList() {
        return this.productcatalog.getProductList();
    }

    public Product getMostExpensiveProduct() {
        ArrayList<Product> productList = this.getProductList();
        Product max = productList.get(0);

        for (Product product : productList) {
            if (product.getTargetPrice() > max.getTargetPrice()) {
                max = product;
            }
        }

        return max;
    }

    public int getTotalSalesIncome() {
        ArrayList<Product> productList = this.getProductList();
        int sum = 0;

        for (Product product : productList) {
            sum += product.getSalesVolume();
        }

        return sum;
    }

    // add supplier product ..

    // update supplier product ...
    @Override
    public String toString() {
        return name;

    }
}
