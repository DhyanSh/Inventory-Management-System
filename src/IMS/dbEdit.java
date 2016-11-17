/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IMS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import static IMS.ChangelogFunc.ChangelogFunc;

/**
 *
 * @author Dhyan
 */
 
 //this is calles when a new data needs to be added
public class dbEdit {
MoreInfo mI; 
db db  = new db();
ChangelogFunc log = new ChangelogFunc();

//This function is called for adding new items in to the databas
    public void getdbEdit(db db) throws SQLException            
    {         
           this.db=db;
            mI = new MoreInfo();
            mI.setVisible(true);
            mI.textEdit();
            mI.jPanel1.remove(mI.editButton1);
            mI.jPanel1.remove(mI.jTextField5);
            mI.jPanel1.remove(mI.jTextField6);
            mI.ApplyButton2.setEnabled(true);
            mI.ApplyButton2.setText("Add");
            mI.jLabel1.setText("Add");
            setjComboBoxCat (mI,db) ;
            
            
  mI.ApplyButton2.addActionListener(new java.awt.event.ActionListener() {
     @Override
     public void actionPerformed(java.awt.event.ActionEvent evt) {
  
         try {
            if(!(mI.jTextField2.getText().isEmpty())){
           addjLabelInfoStock (mI, db);
           JOptionPane.showMessageDialog(null, "New Record Added");
           mI.dispose();}
             else{
            JOptionPane.showMessageDialog(null, "Plese Add Product Name");
            }


         } catch (SQLException ex) {
             Logger.getLogger(dbEdit.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(null, "NO New Record Added");
         }
            }
        });   
    }

// this function is called to edit items that are already in the database	
    public void getdbEdit(db db,String prodID) throws SQLException            
    {         
             this.db=db;
            mI = new MoreInfo();
            mI.setVisible(true);
            getjLabelInfoProduct(mI,db,prodID);
            getjLabelInfoManufac(mI,db,prodID);
            getjLabelInfoDate(mI,db,prodID);
            getjLabelInfoStock(mI,db,prodID);
            getjLabelInfoPrice(mI,db,prodID);
            getjLabelInfoCat(mI,db,prodID);
            
  mI.ApplyButton2.addActionListener(new java.awt.event.ActionListener() {
     @Override
     public void actionPerformed(java.awt.event.ActionEvent evt) {
        boolean error=false;    
        try {
             setjLabelInfoPrice(mI,db,prodID);
         } catch (SQLException ex) {
         //    Logger.getLogger(dbEdit.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, "Price Value must be a Number.\n Price value not Updated", "Price Data Update warning", JOptionPane.WARNING_MESSAGE);
             error = true; 
         }
        try {
             setjLabelInfoStock(mI,db,prodID);
         } catch (SQLException ex) {
            // Logger.getLogger(dbEdit.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(null, "Stock Value must be a Number.\n Stock value not Updated", "Stock Data Update warning", JOptionPane.WARNING_MESSAGE);
         }     
              try {
             setjLabelInfoManufac(mI,db,prodID);
         } catch (SQLException ex) {
       //      Logger.getLogger(dbEdit.class.getName()).log(Level.SEVERE, null, ex);
       error = true; 
         }       
       
       try {
             setjLabelInfoProduct(mI,db,prodID);
         } catch (SQLException ex) {
    //    Logger.getLogger(dbEdit.class.getName()).log(Level.SEVERE, null, ex);
            error = true; 
         }    
              
         try {
             setjLabelInfoDate(mI,db,prodID);
         } catch (SQLException ex) {
     //    Logger.getLogger(dbEdit.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, "Error in Date Values.\n Plese recheck all the Date values.\n Dates not Updated","Date Data Update warning", JOptionPane.WARNING_MESSAGE);
        error = true; 
         }
        
    // System.out.println(error);     
        if (error != true){ 
            JOptionPane.showMessageDialog(null, "All Record Updated");
            mI.dispose();
         }
        else{
        JOptionPane.showMessageDialog(null, "Some Records have not been Updated ","Data Update warning", JOptionPane.WARNING_MESSAGE);
        }
            
        
            }
        });   
    }

  
//this function gets Product information form the database and enters it in to the TextBox of MoreInfo Screen 
private void getjLabelInfoProduct (MoreInfo mI,db db,String prodID) throws SQLException  {  
               
       ResultSet SQLrs = null; // CREAT an empty ResultSet
                 
                 String sqlQ = "Select prodID,prodName,prodSize,SKU,prodDescription"
                         + " FROM  Product"
                        + " WHERE prodID = '"+prodID+"'"; // thhi si an SQL query 
                 
                 try {
                     SQLrs = db.getSQL(sqlQ); // Execute SQL query through the db class and store the results in the crated ResultSet
                 } catch (SQLException ex) {
            //         Logger.getLogger(IMSApp.class.getName()).log(Level.SEVERE, null, ex);
                 }
          SQLrs.next();
          mI.jTextField1.setText(SQLrs.getString(1)); // set textFild to the values form the Resultset 
          mI.jTextField2.setText(SQLrs.getString(2));
          mI.jTextField3.setText(SQLrs.getString(3));
          mI.jTextField4.setText(SQLrs.getString(4));
          mI.jTextArea1.setText(SQLrs.getString(5));
   
 }

 //this function gets Manufacturer information form the database and enters it in to the TextBox of MoreInfo Screen 
private void getjLabelInfoManufac (MoreInfo mI,db db,String prodID) throws SQLException  {  
               
       ResultSet SQLrs = null;
                 
                 String sqlQ1 = "Select manufacID"
                         + " FROM  Product"
                        + " WHERE prodID = '"+prodID+"'";
                 
                 String sqlQ = "Select manufacName,manufacPhone, manufacEmail,manufacAddress"
                                    + " FROM  Manufacturer"
                                         + " WHERE manufacID = ("+ sqlQ1+");";
                 try {
                     SQLrs = db.getSQL(sqlQ);
                 } catch (SQLException ex) {
            //         Logger.getLogger(IMSApp.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 SQLrs.next(); 
          mI.jTextField7.setText(SQLrs.getString(1));
          mI.jTextField8.setText(SQLrs.getString(2));
          mI.jTextField9.setText(SQLrs.getString(3));      
          mI.jTextArea2.setText(SQLrs.getString(4));

 }
 
   //this function gets Dates information form the database and enters it in to the TextBox of MoreInfo Screen   
private void getjLabelInfoDate (MoreInfo mI,db db,String prodID) throws SQLException  {  
      
       ResultSet SQLrs = null;
                 
                 String sqlQ1 = "Select dateID"//, manufacName,manufacPhone, manufacEmail,manufacAddress"
                         + " FROM  Product"
                        + " WHERE prodID = '"+prodID+"'";
                 
                 String sqlQ = "Select manfacDate,lastStockingDate,inventoryCheckDate, lastOrderDate"
                                    + " FROM  Dates"
                                         + " WHERE dateID = ("+sqlQ1+");";
                 try {
                     SQLrs = db.getSQL(sqlQ);
                 } catch (SQLException ex) {
               //      Logger.getLogger(IMSApp.class.getName()).log(Level.SEVERE, null, ex);
                 }
               SQLrs.next();
               
          mI.jTextField17.setText(SQLrs.getString(1));
          mI.jTextField18.setText(SQLrs.getString(2));
          mI.jTextField19.setText(SQLrs.getString(3));      
          mI.jTextField20.setText(SQLrs.getString(4)); 

 }   
//this function gets Stock information form the database and enters it in to the TextBox of MoreInfo Screen 
private void getjLabelInfoStock (MoreInfo mI,db db,String prodID) throws SQLException  {  
        
       ResultSet SQLrs = null;
                 
                 String sqlQ1 = "Select stockID"//, manufacName,manufacPhone, manufacEmail,manufacAddress"
                         + " FROM  Product"
                        + " WHERE prodID = '"+prodID+"'";
                 
                 String sqlQ = "Select inStock,inFreight,sold,shrinkage"
                                    + " FROM  Stock"
                                         + " WHERE stockID = ("+sqlQ1+");";
                 try {
                     SQLrs = db.getSQL(sqlQ);
                 } catch (SQLException ex) {
              //       Logger.getLogger(IMSApp.class.getName()).log(Level.SEVERE, null, ex);
                 }
               SQLrs.next();
   
          mI.jTextField10.setText(SQLrs.getString(1));
          mI.jTextField11.setText(SQLrs.getString(2));
          mI.jTextField12.setText(SQLrs.getString(3));      
          mI.jTextField13.setText(SQLrs.getString(4)); 


 } 
  //this function gets Price information form the database and enters it in to the TextBox of MoreInfo Screen    
private void getjLabelInfoPrice (MoreInfo mI,db db,String prodID) throws SQLException  {  
  
       ResultSet SQLrs = null;
                 
                 String sqlQ1 = "Select priceID"//, manufacName,manufacPhone, manufacEmail,manufacAddress"
                         + " FROM  Product"
                        + " WHERE prodID = '"+prodID+"'";
                 
                 String sqlQ = "Select prodSellPrice,prodBuyPrice,prodStorPrice"
                                    + " FROM  Price"
                                         + " WHERE priceID = ("+sqlQ1+");";
                 try {
                     SQLrs = db.getSQL(sqlQ);
                 } catch (SQLException ex) {
            //         Logger.getLogger(IMSApp.class.getName()).log(Level.SEVERE, null, ex);
                 }
               SQLrs.next();
               
          mI.jTextField14.setText(SQLrs.getString(1));
          mI.jTextField15.setText(SQLrs.getString(2));
          mI.jTextField16.setText(SQLrs.getString(3));

 }    
  //this function gets Category/Subcategory information form the database and enters it in to the TextBox of MoreInfo Screen    
private void getjLabelInfoCat (MoreInfo mI,db db,String prodID) throws SQLException  {  
  
       ResultSet SQLrs = null;
                 
                 String sqlQ1 = "Select subcatID"//, manufacName,manufacPhone, manufacEmail,manufacAddress"
                         + " FROM  Product"
                        + " WHERE prodID = '"+prodID+"'";
                 
                 String sqlQ = "Select catName,subcatName"
                                    + " FROM  Subcategory sc, Category c"
                                         + " WHERE sc.subcatID = ("+sqlQ1+") AND c.catID = sc.catID;";
                 try {
                     SQLrs = db.getSQL(sqlQ);
                 } catch (SQLException ex) {
             //        Logger.getLogger(IMSApp.class.getName()).log(Level.SEVERE, null, ex);
                 }
               SQLrs.next();
               
          mI.jTextField5.setText(SQLrs.getString(1));
          mI.jTextField6.setText(SQLrs.getString(2));
 }    
    
 //this function gets Category/Subcategory information form the database and 
 //creats ComboBox in the add function of the program  
private void setjComboBoxCat (MoreInfo mI, db db) throws SQLException  {  
  
       ResultSet SQLrs = null;
          
                 String sqlQ = "Select catName"
                                    + " FROM Category;";

                 try {
                     SQLrs = db.getSQL(sqlQ);
                 } catch (SQLException ex) {
              //       Logger.getLogger(IMSApp.class.getName()).log(Level.SEVERE, null, ex);
                 }
              
              while(SQLrs.next()){
               mI.jComboBox1.addItem(SQLrs.getString(1));
              }

      mI.jComboBox1.addActionListener(new java.awt.event.ActionListener() {
     @Override
     public void actionPerformed(java.awt.event.ActionEvent evt) {
              ResultSet SQLrs1 = null;  
            int catID = mI.jComboBox1.getSelectedIndex()+1;
            
                String sqlQ1 = "Select subcatName"
                    + " FROM Subcategory WHERE catID = '"+catID+"';";
       
                try {
                    SQLrs1 = db.getSQL(sqlQ1);
                 } catch (SQLException ex) {
              //       Logger.getLogger(IMSApp.class.getName()).log(Level.SEVERE, null, ex);
                 }
         try {
             mI.jComboBox2.removeAllItems();
             while(SQLrs1.next()){
                 mI.jComboBox2.addItem(SQLrs1.getString(1));
             }
         } catch (SQLException ex) {
     //        Logger.getLogger(dbEdit.class.getName()).log(Level.SEVERE, null, ex);
         }
            }
        });           
       
              

      
 }      
   
//this function gets Category/Subcategory information form the TextBox of Edit Screen and enters/edits it in to the database     
private void setjLabelInfoPrice (MoreInfo mI,db db,String prodID) throws SQLException  {  
  
       ResultSet SQLrs = null;
            String stOld ,stNew;     
                 String sqlQ1 = "Select priceID"
                         + " FROM  Product"
                        + " WHERE prodID = '"+prodID+"'";
                 
                 String sqlQ = "Select prodSellPrice,prodBuyPrice,prodStorPrice"
                                    + " FROM  Price"
                                         + " WHERE priceID = ("+sqlQ1+");";
                 try {
                     SQLrs = db.getSQL(sqlQ);
                 } catch (SQLException ex) {
                //     Logger.getLogger(IMSApp.class.getName()).log(Level.SEVERE, null, ex);
                 }
          SQLrs.next();
          
           stOld = SQLrs.getString(1); // get value form the Resultset 
           stNew = mI.jTextField14.getText(); // get value form the TextField 
           if (!stOld.equals(stNew)) // Compare the two values are not equal, add an entry in the change log  
         ChangelogFunc(prodID,stOld,stNew,"Price","Sell Price"); 
         
           stOld = SQLrs.getString(2);
           stNew = mI.jTextField15.getText();
            if (!stOld.equals(stNew))
         ChangelogFunc(prodID,stOld,stNew,"Price","Buy Price"); 
         
         
           stOld = SQLrs.getString(3);
           stNew = mI.jTextField16.getText();  
           if (!stOld.equals(stNew))
           ChangelogFunc(prodID,stOld,stNew,"Price","Buy Price"); 
         
          SQLrs.updateString(1,mI.jTextField14.getText()); // update database value to the current TextField value 
          SQLrs.updateString(2,mI.jTextField15.getText());
          SQLrs.updateString(3,mI.jTextField16.getText());
        
          SQLrs.updateRow();
          SQLrs.refreshRow();
 }     

//this function gets Category/Subcategory information form the TextBox of Edit Screen and enters/edits it in to the database  
private void setjLabelInfoStock (MoreInfo mI,db db,String prodID) throws SQLException  {  
  
     ResultSet SQLrs = null;
      String stOld ,stNew;               
                 String sqlQ1 = "Select stockID"//, manufacName,manufacPhone, manufacEmail,manufacAddress"
                         + " FROM  Product"
                        + " WHERE prodID = '"+prodID+"'";
                 
                 String sqlQ = "Select inStock,inFreight,sold,shrinkage"
                                    + " FROM  Stock"
                                         + " WHERE stockID = ("+sqlQ1+");";
                 try {
                     SQLrs = db.getSQL(sqlQ);
                 } catch (SQLException ex) {
             //        Logger.getLogger(IMSApp.class.getName()).log(Level.SEVERE, null, ex);
                 }
               SQLrs.next();
               
           stOld = SQLrs.getString(1);
           stNew = mI.jTextField10.getText();  
           if (!stOld.equals(stNew))
           ChangelogFunc(prodID,stOld,stNew,"Stock","In Stock");    
               
           stOld = SQLrs.getString(2);
           stNew = mI.jTextField11.getText();  
           if (!stOld.equals(stNew))
           ChangelogFunc(prodID,stOld,stNew,"Stock","In Freight");      
           
           stOld = SQLrs.getString(3);
           stNew = mI.jTextField12.getText();  
           if (!stOld.equals(stNew))
           ChangelogFunc(prodID,stOld,stNew,"Stock","Sold");  
           
           
           stOld = SQLrs.getString(4);
           stNew = mI.jTextField13.getText();  
           if (!stOld.equals(stNew))
           ChangelogFunc(prodID,stOld,stNew,"Stock","Shrinkage");  
              
          SQLrs.updateString(1,mI.jTextField10.getText());
          SQLrs.updateString(2,mI.jTextField11.getText());
          SQLrs.updateString(3,mI.jTextField12.getText());
          SQLrs.updateString(4,mI.jTextField13.getText());
          
          SQLrs.updateRow();
          SQLrs.refreshRow();
          

 }    
 
 //this function gets Category/Subcategory information form the TextBox of Edit Screen and enters/edits it in to the database  
private void setjLabelInfoManufac (MoreInfo mI,db db,String prodID) throws SQLException  {  
  
       ResultSet SQLrs = null;
       String stOld ,stNew;             
                 String sqlQ1 = "Select manufacID"//, manufacName,manufacPhone, manufacEmail,manufacAddress"
                         + " FROM  Product"
                        + " WHERE prodID = '"+prodID+"'";
                 
                 String sqlQ = "Select manufacName,manufacPhone, manufacEmail,manufacAddress"
                                    + " FROM  Manufacturer"
                                         + " WHERE manufacID = ("+ sqlQ1+");";
                 try {
                     SQLrs = db.getSQL(sqlQ);
                 } catch (SQLException ex) {
            //         Logger.getLogger(IMSApp.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 SQLrs.next(); 
                 
            stOld = SQLrs.getString(1);
           stNew = mI.jTextField7.getText();  
           if (!stOld.equals(stNew))
           ChangelogFunc(prodID,stOld,stNew,"Manufacturer","Manufacturer Name");          
                 
            stOld = SQLrs.getString(2);
           stNew = mI.jTextField8.getText();  
           if (!stOld.equals(stNew))
           ChangelogFunc(prodID,stOld,stNew,"Manufacturer","Manufacturer Phone");        
          
           stOld = SQLrs.getString(3);
           stNew = mI.jTextField9.getText();  
           if (!stOld.equals(stNew))
           ChangelogFunc(prodID,stOld,stNew,"Manufacturer","Manufacturer Email");  
           
           stOld = SQLrs.getString(4);
           stNew = mI.jTextArea2.getText();  
           if (!stOld.equals(stNew))
           ChangelogFunc(prodID,stOld,stNew,"Manufacturer","Manufacturer Address");  
              
          SQLrs.updateString(1,mI.jTextField7.getText());
          SQLrs.updateString(2,mI.jTextField8.getText());
          SQLrs.updateString(3,mI.jTextField9.getText());
          SQLrs.updateString(4,mI.jTextArea2.getText());
           
          SQLrs.updateRow();
          SQLrs.refreshRow();
 }    

//this function gets Category/Subcategory information form the TextBox of Edit Screen and enters/edits it in to the database   
private void setjLabelInfoProduct (MoreInfo mI,db db,String prodID) throws SQLException  {  
  
       ResultSet SQLrs = null;
        String stOld ,stNew;           
                 String sqlQ = "Select prodID,prodName,prodSize,SKU,prodDescription"
                         + " FROM  Product"
                        + " WHERE prodID = '"+prodID+"'";
                 
                 try {
                     SQLrs = db.getSQL(sqlQ);
                 } catch (SQLException ex) {
               //      Logger.getLogger(IMSApp.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 SQLrs.next();
                 
            stOld = SQLrs.getString(2);
           stNew = mI.jTextField2.getText();  
           if (!stOld.equals(stNew))
           ChangelogFunc(prodID,stOld,stNew,"Product","Product Name"); 

            stOld = SQLrs.getString(3);
           stNew = mI.jTextField3.getText();  
           if (!stOld.equals(stNew))
           ChangelogFunc(prodID,stOld,stNew,"Product","Product Size"); 

            stOld = SQLrs.getString(4);
           stNew = mI.jTextField4.getText();  
           if (!stOld.equals(stNew))
           ChangelogFunc(prodID,stOld,stNew,"Product","SKU#");
           
            stOld = SQLrs.getString(5);
           stNew = mI.jTextArea1.getText();  
           if (!stOld.equals(stNew))
           ChangelogFunc(prodID,stOld,stNew,"Product","Description");  
                 
                 
                 
              
          SQLrs.updateString(1,mI.jTextField1.getText());
          SQLrs.updateString(2,mI.jTextField2.getText());
          SQLrs.updateString(3,mI.jTextField3.getText());
          SQLrs.updateString(4,mI.jTextField4.getText());
          SQLrs.updateString(5,mI.jTextArea1.getText());
          
          SQLrs.updateRow();
          SQLrs.refreshRow();
          

 }       

//this function gets Category/Subcategory information form the TextBox of Edit Screen and enters/edits it in to the database   
private void setjLabelInfoDate (MoreInfo mI,db db,String prodID) throws SQLException  {  
  
     ResultSet SQLrs = null;
     String stOld ,stNew;             
                 String sqlQ1 = "Select dateID"//, manufacName,manufacPhone, manufacEmail,manufacAddress"
                         + " FROM  Product"
                        + " WHERE prodID = '"+prodID+"'";
                 
                 String sqlQ = "Select manfacDate,lastStockingDate,inventoryCheckDate, lastOrderDate"
                                    + " FROM  Dates"
                                         + " WHERE dateID = ("+sqlQ1+");";
                 try {
                     SQLrs = db.getSQL(sqlQ);
                 } catch (SQLException ex) {
            //         Logger.getLogger(IMSApp.class.getName()).log(Level.SEVERE, null, ex);
                 }
               SQLrs.next();
               
           stOld = SQLrs.getString(1);
           stNew = mI.jTextField17.getText();  
           if (!stOld.equals(stNew))
           ChangelogFunc(prodID,stOld,stNew,"Dates","Manufacturer Date");  
               
           stOld = SQLrs.getString(2);
           stNew = mI.jTextField18.getText();  
           if (!stOld.equals(stNew))
           ChangelogFunc(prodID,stOld,stNew,"Dates","Last Stocking Date");  
           
           stOld = SQLrs.getString(3);
           stNew = mI.jTextField19.getText();  
           if (!stOld.equals(stNew))
           ChangelogFunc(prodID,stOld,stNew,"Dates","Last Inventory CheckDate");  
           
           stOld = SQLrs.getString(4);
           stNew = mI.jTextField20.getText();  
           if (!stOld.equals(stNew))
           ChangelogFunc(prodID,stOld,stNew,"Dates","Last Order Date");  
                
               
          SQLrs.updateString(1,mI.jTextField17.getText());
          SQLrs.updateString(2,mI.jTextField18.getText());
          SQLrs.updateString(3,mI.jTextField19.getText());
          SQLrs.updateString(4,mI.jTextField20.getText());
          
          SQLrs.updateRow();
          SQLrs.refreshRow();
          

 }  


 // This function gets all the values for add screen TextBoxs and adds the new item in the database  
  private void addjLabelInfoStock (MoreInfo mI,db db) throws SQLException  {  
/////////////////////////////////////
      
      
      
      
 //////////////////////////////////
// create an SQL command that can insert value form the textFields in to the database
   String  sqlQ = "INSERT INTO Stock(inStock,inFreight,sold,shrinkage)"
          + " VALUES ("+ mI.jTextField10.getText() +","+ mI.jTextField11.getText() +","+ mI.jTextField12.getText()+"," +mI.jTextField13.getText()+");";
       //     System.out.println(sqlQ);
     ResultSet SQLrs =null;
                try {
                     db.stment.executeUpdate(sqlQ); // execute the created command 
                     SQLrs = db.getSQL("SELECT MAX(stockID) FROM Stock"); // get the newly created ID
                 } catch (SQLException ex) {
             //        Logger.getLogger(IMSApp.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 SQLrs.next();  
  String  stockID = SQLrs.getString(1); // Set the new Id to string to reference 
  
////////////////////////////////////////
    
       sqlQ = "INSERT INTO Price(prodSellPrice,prodBuyPrice,prodStorPrice)"
          + " VALUES ("+ mI.jTextField14.getText() +","+ mI.jTextField15.getText() +","+ mI.jTextField16.getText()+");";
       // System.out.println(sqlQ);
           // db.stment.executeUpdate(sqlQ);
           SQLrs =null;
                try {
					db.stment.executeUpdate(sqlQ);
                     SQLrs = db.getSQL("SELECT MAX(priceID) FROM Price");
                 } catch (SQLException ex) {
               //      Logger.getLogger(IMSApp.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 SQLrs.next();  
    String priceID = SQLrs.getString(1); 
            
 //////////////////////////////////////// 

      sqlQ = "INSERT INTO Dates(manfacDate,lastStockingDate,inventoryCheckDate, lastOrderDate)"
          + " VALUES ("+ mI.jTextField17.getText()+","+ mI.jTextField18.getText() +","+ mI.jTextField19.getText()+"," +mI.jTextField20.getText()+");";
          
           SQLrs =null;
                try {
                  //          System.out.println(sqlQ);
                          db.stment.executeUpdate(sqlQ);
                     SQLrs = db.getSQL("SELECT MAX(dateID) FROM Dates");
                 } catch (SQLException ex) {
               //      Logger.getLogger(IMSApp.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 SQLrs.next();  
    String dateID = SQLrs.getString(1); 
            
 ////////////////////////////////////////
  try {
sqlQ ="SELECT subcatID FROM Subcategory"+" WHERE subcatName = '"+mI.jComboBox2.getSelectedItem().toString()+"';";}
  catch (Exception ex) {
  JOptionPane.showMessageDialog(null, "Plese Select Subcategory ","Data Add Error", JOptionPane.ERROR_MESSAGE); 
  }
      SQLrs =null;
                try {
                     SQLrs = db.stment.executeQuery(sqlQ);
                 } catch (SQLException ex) {  
               
              // Logger.getLogger(IMSApp.class.getName()).log(Level.SEVERE, null, ex);
                 }
    SQLrs.next(); 
    String subcatID = SQLrs.getString(1); 
 ////////////////////////////////////////  
 
String manufacID="";
  SQLrs =null;
    try {
sqlQ ="SELECT manufacID FROM Manufacturer"+" WHERE manufacName = '"+mI.jTextField7.getText()+"';";}
  catch (Exception ex) {
  JOptionPane.showMessageDialog(null, "Plese ADD  manufacName ","Data Add Error", JOptionPane.ERROR_MESSAGE); 
  }
 // System.out.println(sqlQ);
 
       try {
            SQLrs = db.stment.executeQuery(sqlQ);
        } catch (SQLException ex) {
            Logger.getLogger(IMSApp.class.getName()).log(Level.SEVERE, null, ex);
     // System.out.println("ERROR1: "+sqlQ);
        }

       if (!SQLrs.next()){
      //     System.out.println("SQLrs.wasNull"); 
       if (mI.jTextField7.getText().isEmpty()){
           mI.jTextField7.setText("Unknown");
       } 

          sqlQ = "INSERT INTO Manufacturer(manufacName,manufacPhone,manufacEmail,manufacAddress)"
          + " VALUES ('"+ mI.jTextField7.getText() +"','"+ mI.jTextField8.getText() +"','"+ mI.jTextField9.getText()+"','" +mI.jTextArea2.getText()+"');";
                try {
                 //      System.out.println(sqlQ);
                        db.stment.executeUpdate(sqlQ);
                       SQLrs = db.getSQL("SELECT MAX(manufacID) FROM Manufacturer");

                 } catch (SQLException ex) {
                     Logger.getLogger(IMSApp.class.getName()).log(Level.SEVERE, null, ex);
             //  System.out.println("ERROR2: "+sqlQ);
                 }
                 SQLrs.next();  
                 manufacID = SQLrs.getString(1); 
        }  
       
       else {
          try {  SQLrs.next();  
          manufacID = SQLrs.getString(1);  }
          catch (SQLException ex) {
              
          }
       }
 
  ////////////////////////////////////////  
 /////////////////////////////////////////  

       
 sqlQ = "INSERT INTO Product(prodName,prodSize,SKU,prodDescription,subcatID,dateID,stockID,manufacID,priceID)"
          + " VALUES ('"+ mI.jTextField2.getText() +"','"+ mI.jTextField3.getText() +"','"+ mI.jTextField4.getText()+"','"+mI.jTextArea1.getText()+
         "','"+subcatID+"','"+dateID+"','"+ stockID+"','"+ manufacID+"','"+priceID+"');";     
 //System.out.println(sqlQ);
 try {    db.stment.executeUpdate(sqlQ);
 //System.out.println(sqlQ);
 }
    catch (SQLException ex) {
  // Logger.getLogger(IMSApp.class.getName()).log(Level.SEVERE, null, ex);
    }
 
   
  }
}
