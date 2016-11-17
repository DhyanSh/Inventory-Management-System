package IMS;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Dhyan
 */
public class db {
       Statement stment = null;
      ResultSet rsPrice,rsStock,rsDates,rsCategory,rsManufacturer,rsProduct;
      Connection conn;
      
      //creates the database connection
    public  db()   {
          try  {
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
         conn = DriverManager.getConnection("jdbc:ucanaccess://./NCIXdb(1).accdb");
         stment = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);	 
      
     }
     catch(ClassNotFoundException | SQLException err) {
       System.out.println(err);
     }

    }   

    //allows the execution of an SQL statement passed into it in the form of a string
    public ResultSet getSQL (String df ) throws SQLException  {
      ResultSet SQLresult = null;
      try  {
        SQLresult = stment.executeQuery(df);

      }
          catch(Exception err) {
       System.out.println(err);
     }
    return SQLresult;     
    }

}