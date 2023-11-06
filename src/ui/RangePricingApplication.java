/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import model.Business.Business;
import model.Business.ConfigureABusiness;
import model.ProductManagement.ProductCatalog;
import model.ProductManagement.ProductsReport;
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

    Business business = ConfigureABusiness.createABusinessAndLoadALotOfData("Amazon", 20, 10, 30, 100, 10);
    business.printShortInfo();

  }


}
