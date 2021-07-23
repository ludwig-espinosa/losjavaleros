/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import static Modelo.Conexion.conn;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultaArticulo {
    public boolean registrar(Articulo art){
        
        PreparedStatement ps=null;
        String sql="INSERT INTO Articulos (nombre,codigo,cantidad,costo,proveedor,fecha,descripcion) VALUES(?,?,?,?,?,?,?)";
        try{
            ps=conn.prepareStatement(sql);
            ps.setString(1,art.getNombre());
            ps.setInt(2,art.getCodigo());
            ps.setInt(3,art.getCantidad());
            ps.setInt(4,art.getCosto());
            ps.setString(5,art.getProveedor());
            ps.setDate(6, (Date) art.getFecha());
            ps.setString(7,art.getDescripcion());
            ps.execute();
            System.out.println("registrado");
            return true;
        }catch (SQLException e){
            System.out.println(e);
            return false;       
        }
    }
    
     public boolean modificar(Articulo art){
        
        PreparedStatement ps=null;
        String sql="UPDATE Articulos SET nombre=?,cantidad=?,costo=?,proveedor=?,fecha=?,decripcion=? where codigo=?";
        try{
            ps=conn.prepareStatement(sql);
            ps.setString(1,art.getNombre());
            ps.setInt(2,art.getCodigo());
            ps.setInt(3,art.getCantidad());
            ps.setInt(4,art.getCosto());
            ps.setString(5,art.getProveedor());
            ps.setDate(6, (Date) art.getFecha());
            ps.setString(7,art.getDescripcion());
            ps.execute();
            System.out.println("registrado");
            return true;
        }catch (SQLException e){
            System.out.println(e);
            return false;       
        }
     }
      public boolean buscar(Articulo art){
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "SELECT * FROM Articulos WHERE codigo=?  ";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setInt(1, art.getCodigo());
          rs = ps.executeQuery();
          if (!rs.absolute(1)) {
              System.out.println("Codigo no encontrado");
            return false; 
          }
          return true;
      } catch (SQLException e){
          return false;
      }
        
        
    }
   
    
    
    
    
    
    
}
