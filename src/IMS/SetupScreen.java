/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IMS;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


/**
 *
 * @author Dhyan
 */
public class SetupScreen {
    
    
   public void jTableSetup (db data, IMSApp gui) throws SQLException  {
       
       ResultSet SQLrs;

   String sqlQ = "Select  prodID, prodName ,prodSellPrice,inStock, catName,subcatName,SKU"
               + " FROM Product pro, Price pri, Stock s, Category c , Subcategory sc"
               + " WHERE pro.priceID = pri.priceID AND pro.stockID = s.stockID AND pro.subcatID =sc.subcatID AND sc.catID = c.catID;";

             SQLrs = data.getSQL(sqlQ);     


// populates the Jtable with values form the database 
       DefaultTableModel dtm = (DefaultTableModel) gui.jTable1.getModel(); 
      dtm.setRowCount(0);
      while (SQLrs.next()) {   
        dtm.addRow(new Object[] { SQLrs.getString(1), SQLrs.getString(2), SQLrs.getString(3), SQLrs.getString(4),SQLrs.getString(5),SQLrs.getString(6),SQLrs.getString(7)});       
                           }    
      
   
    gui.jTable1.setAutoCreateRowSorter(true);
TableRowSorter<DefaultTableModel> rowSorter = (TableRowSorter<DefaultTableModel>)gui.jTable1.getRowSorter();
rowSorter.setComparator(5, new Comparator<String>() {

        @Override
        public int compare(String o1, String o2)
        {
            return Integer.parseInt(o1) - Integer.parseInt(o2);
        }

    });  
      
      
      
      
      }
      
    public void jLabelInfoManufac (db data1, IMSApp gui1,String prodID) throws SQLException  {  
               
       ResultSet SQLrs = null;
                 
                 String sqlQ1 = "Select manufacID"
                         + " FROM  Product"
                        + " WHERE prodID = '"+prodID+"'";
                 
                 String sqlQ = "Select manufacName,manufacPhone, manufacEmail,manufacAddress"
                                    + " FROM  Manufacturer"
                                         + " WHERE manufacID = ("+ sqlQ1+");";
                 try {
                     SQLrs = data1.getSQL(sqlQ);
                 } catch (SQLException ex) {
                 //    Logger.getLogger(IMSApp.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 SQLrs.next();
                    
               gui1.jLabel2.setText(SQLrs.getString(1));
               gui1.jLabel3.setText("Phone: "+SQLrs.getString(2));
               gui1.jLabel4.setText("Email: "+SQLrs.getString(3));
               gui1.jLabel5.setText(SQLrs.getString(4));

 }
    
     public void jLabelInfoDate (db data1, IMSApp gui1,String prodID) throws SQLException  {  

        
       ResultSet SQLrs = null;
                 
                 String sqlQ1 = "Select dateID"
                         + " FROM  Product"
                        + " WHERE prodID = '"+prodID+"'";
                 
                 String sqlQ = "Select lastStockingDate,inventoryCheckDate, lastOrderDate"
                                    + " FROM  Dates"
                                         + " WHERE dateID = ("+sqlQ1+");";
                 try {
                     SQLrs = data1.getSQL(sqlQ);
                 } catch (SQLException ex) {
                  //   Logger.getLogger(IMSApp.class.getName()).log(Level.SEVERE, null, ex);
                 }
               SQLrs.next();
              //  gui1.j    
               gui1.jLabel10.setText(SQLrs.getString(1));
               gui1.jLabel11.setText(SQLrs.getString(2));
               gui1.jLabel12.setText(SQLrs.getString(3));
             //   gui1.jLabel5.setText("Address: "+SQLrs.getString(4));

 }   
    
    public void jLabelInfoStock (db data1, IMSApp gui1,String prodID) throws SQLException  {  
        
       ResultSet SQLrs = null;
                 
                 String sqlQ1 = "Select stockID"
                         + " FROM  Product"
                        + " WHERE prodID = '"+prodID+"'";
                 
                 String sqlQ = "Select inStock,inFreight,sold,shrinkage"
                                    + " FROM  Stock"
                                         + " WHERE stockID = ("+sqlQ1+");";
                 try {
                     SQLrs = data1.getSQL(sqlQ);
                 } catch (SQLException ex) {
             //        Logger.getLogger(IMSApp.class.getName()).log(Level.SEVERE, null, ex);
                 }
               SQLrs.next();
   
               gui1.jLabel14.setText("In Stock: "+SQLrs.getString(1));
               gui1.jLabel15.setText("In Freight: "+SQLrs.getString(2));
               gui1.jLabel17.setText("Total Sold: "+SQLrs.getString(3));
               gui1.jLabel18.setText("Shrinkage: "+SQLrs.getString(4));


 } 
    
    public void jLabelInfoPrice (db data1, IMSApp gui1,String prodID) throws SQLException  {  
        
        
       ResultSet SQLrs = null;
                 
                 String sqlQ1 = "Select priceID"
                         + " FROM  Product"
                        + " WHERE prodID = '"+prodID+"'";
                 
                 String sqlQ = "Select prodSellPrice,prodBuyPrice,prodStorPrice"
                                    + " FROM  Price"
                                         + " WHERE priceID = ("+sqlQ1+");";
                 try {
                     SQLrs = data1.getSQL(sqlQ);
                 } catch (SQLException ex) {
                 //    Logger.getLogger(IMSApp.class.getName()).log(Level.SEVERE, null, ex);
                 }
               SQLrs.next();
               
               gui1.jLabel19.setText("Sell: $"+SQLrs.getString(1));
               gui1.jLabel20.setText("Buy: $"+SQLrs.getString(2));
               gui1.jLabel21.setText("Store: $"+SQLrs.getString(3));

 }    
    
 public void jTextDiscript (db data1, IMSApp gui1,String prodID) throws SQLException  {  
     
       ResultSet SQLrs = null;
                 
                 String sqlQ = "Select prodDescription"
                         + " FROM  Product"
                        + " WHERE prodID = '"+prodID+"'";
                 try {
                     SQLrs = data1.getSQL(sqlQ);
                 } catch (SQLException ex) {
               //      Logger.getLogger(IMSApp.class.getName()).log(Level.SEVERE, null, ex);
                 }
             
               SQLrs.next();
               
              gui1.jTextArea1.setText(SQLrs.getString(1));

 }     
 
    
}
         
            
    

    
    

