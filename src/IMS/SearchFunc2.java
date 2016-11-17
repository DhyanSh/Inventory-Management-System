/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IMS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Kristofer
 */
public class SearchFunc2 {
    
        //this function performs a narrow search
        //this means it searches for exactly what is inputted in st
        public static ResultSet SearchFunc2(String st, String table, String col, Connection conn){
        Statement stment = null;
        String query = "select * from " + table +" where " + col +" like '" + st + "'";
        ResultSet rs;
        try{
       stment = conn.createStatement();
       rs = stment.executeQuery(query);
        return rs;
        }catch(Exception e){
            
            return null;
        }
        
    }
}
