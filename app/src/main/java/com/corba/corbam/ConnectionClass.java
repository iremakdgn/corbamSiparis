package com.corba.corbam;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 */
public class ConnectionClass {

    String classs = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://192.168.1.117:3306/corbacim";
    String un = "admin";
    String password = "";

    public static Connection connectDatabase(){
        Connection con=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://192.168.1.117:3306/corbacim?zeroDateTimeBehavior=convertToNull&characterEncoding=UTF-8","admin","");
            //con=DriverManager.getConnection("jdbc:mysql://192.168.1.101:3306/corbacim?zeroDateTimeBehavior=convertToNull&characterEncoding=UTF-8","corba","JsvuLtk2iXhGtyXR");

            // con=DriverManager.getConnection("jdbc:mysql://78.186.219.143:3306/corbacim?zeroDateTimeBehavior=convertToNull&characterEncoding=UTF-8","corba","JsvuLtk2iXhGtyXR");
        } catch(Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("basarili");
        return con;
    }

}