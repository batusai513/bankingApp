/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.sql.*;

/**
 *
 * @author Richard
 */
public class Db {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/UserDB";
    
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "password";
    private static Connection connection;
    
    public static Connection getConnection(){
        if(connection != null){
            return connection;
        }else{
            try{
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
