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
        String sql="INSERT INTO Articulos (nombre,cantidad,costo,proveedor,fecha) VALUES(?,?,?,?,?)";
        try{
            java.sql.Date date = new java.sql.Date(art.getFecha().getTime());
            ps=conn.prepareStatement(sql);
            ps.setString(1,art.getNombre());
            ps.setInt(2,art.getCantidad());
            ps.setInt(3,art.getCosto());
            ps.setString(4,art.getProveedor());
            ps.setDate(5, date);
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
        java.sql.Date date = new java.sql.Date(art.getFecha().getTime());
        String sql="UPDATE Articulos SET cantidad=?,costo=?,proveedor=?,fecha=? where nombre=?";
        try{
            ps=conn.prepareStatement(sql);
            ps.setInt(1,art.getCantidad());
            ps.setInt(2,art.getCosto());
            ps.setString(3,art.getProveedor());
            ps.setDate(4, date);
            ps.setString(5,art.getNombre());
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
      String sql = "SELECT * FROM Articulos WHERE nombre=?  ";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setString(1, art.getNombre());
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
   
    public ResultSet llamarActivos() {
         PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "SELECT * FROM Articulos WHERE estado=1";
      try {
          ps =  conn.prepareStatement(sql);
          rs = ps.executeQuery();
          return rs;
      } catch (SQLException e){
          return null;
      }
    }
    
     public ResultSet llamarTodos() {
         PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "SELECT * FROM Articulos";
      try {
          ps =  conn.prepareStatement(sql);
          rs = ps.executeQuery();
          return rs;
      } catch (SQLException e){
          return null;
      }
    }
    
    
    
    public int buscarPrecio(String valor) {
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "SELECT * FROM Articulos WHERE nombre=?";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setString(1, valor);
          rs = ps.executeQuery();
          rs.next();
          return rs.getInt("precio");
      } catch (SQLException e){
          return -1;
      }
    }
    
    public int buscarIdPorNombre(String nombre) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id;
        String sql = "SELECT * FROM Articulos WHERE nombre=?";
        try {
            ps =  conn.prepareStatement(sql);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            rs.next();
            if (!rs.absolute(1)) {
                System.out.println("articulo id no encontrado");
              return -1; 
            }else{
                id = Integer.parseInt(rs.getString(1));
                System.out.println(id);
                return id;
            }        
        } catch (SQLException e){
            return -1;
        }
    }
}
