/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Usuario
 */
public class ConsultaComuna extends Conexion {
    
    public boolean registrar(Comuna com){
    
      PreparedStatement ps = null;
      String sql = "INSERT INTO comunas (cod_comuna, nombre, estado) VALUES(?,?,?)";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setString(1, com.getCodigo());
          ps.setString(2, com.getNombre());
          ps.setBoolean(3, com.isEstado());
          ps.execute();
          System.out.println("registrado");
          return true;
      } catch (SQLException e){
          System.out.println(e);
          return false;
      }
    }

    public boolean modificar(Comuna com){
    
      PreparedStatement ps = null;
      String sql = "UPDATE Comunas SET nombre=?, estado=? WHERE cod_comuna=?";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setString(1, com.getNombre());
          ps.setBoolean(2, com.isEstado());
          ps.setString(3, com.getCodigo());
          ps.execute();
          return true;
      } catch (SQLException e){
          return false;
      }
    }
    public boolean buscar(Comuna com){
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "SELECT * FROM Comuna WHERE cod_comuna=?";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setString(1, com.getCodigo());
          rs = ps.executeQuery();
          if (!rs.absolute(1)) {
              System.out.println("comuna no encontrado");
            return false; 
          }
          return true;
      } catch (SQLException e){
          return false;
      }
    }
    
     public ResultSet llamarTodos(){
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "SELECT * FROM Comunas";
      try {
          ps =  conn.prepareStatement(sql);
          rs = ps.executeQuery();
          return rs;
      } catch (SQLException e){
          return null;
      }
    }
}
