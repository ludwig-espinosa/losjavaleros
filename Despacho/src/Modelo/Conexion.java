
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    private static Connection conn;
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String usuario = "uxjgvc882rjz86cl";
    private static final String pass = "wgMnwIN4ORfjfZOp4gnm";
    private static final String url = "mysql://uxjgvc882rjz86cl:wgMnwIN4ORfjfZOp4gnm@bhhkcs5kos45dcpink4e-mysql.services.clever-cloud.com:3306/bhhkcs5kos45dcpink4e";

    public Conexion(){
        conn = null ;
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url, usuario, pass);   
            if(conn != null){
                System.out.println("Conexion lograda");
            }
        } catch (ClassNotFoundException | SQLException e){
            System.out.println("error al conectar " + e);
        }
    }

    public static Connection getConn() {
        return conn;
    }

    public static void desconectar(){
        conn = null;
        if(conn == null){
            System.out.print("Desconexion Lista");
        }
    }
}
