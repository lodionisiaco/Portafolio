/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author camil
 */
public class Conexion {   //singleton .......clase conexion    ......
    
    public static Connection connection;
    //private static String usuario = "odontologia";
    //private static String password = "1234";
    //private static String url = "jdbc:oracle:thin:@localhost:1521:portafolioBD";
    
    public static Connection getConnection(){
        try {
            if(connection == null){
                Runtime.getRuntime().addShutdownHook(new getClose());
                Class.forName("oracle.jdbc.driver.OracleDriver");
                connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:portafolioBD","odontologia","1234");
            }
            return connection; 
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Conexion fallida",e);
        }
       
    }
    
    static class getClose extends Thread{
        @Override
        public void run(){
            
            try {
                Connection conn = Conexion.getConnection();
                conn.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
        
  
    
    
    
}
