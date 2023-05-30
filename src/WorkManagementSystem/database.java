/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WorkManagementSystem;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Admin
 */
public class database {
    
    
    public static Connection connectDb(){
    
        try{
            Class.forName("com.mysql.jbdc.Driver");
            
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:/management","root", "");
            return connect;
        }catch(Exception e){e.printStackTrace();}
        return null;
    }
}
