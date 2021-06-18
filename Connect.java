package Grocerry_Management;
import java.sql.Connection;
import java.sql.DriverManager;


import com.mysql.jdbc.Driver;

public class Connect {
    static Connection con;
    public static Connection CreateCon() throws ClassNotFoundException {
        //Load the driver

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String user = "root";
            String pass = "Please enter the password over here";
            String url = "Please enter the link to the database over here.";
            con = DriverManager.getConnection(url,user,pass);
        }
       catch (Exception e)
        {
            e.printStackTrace();
        }
        return  con;
    }
}
