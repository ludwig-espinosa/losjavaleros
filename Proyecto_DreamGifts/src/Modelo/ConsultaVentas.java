/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;
import static Modelo.Conexion.conn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author lespinosa
 */
public class ConsultaVentas {
  public boolean registrar(Venta vent){
    
      PreparedStatement ps = null;
      String sql = "INSERT INTO Ventas (ID_Cliente, red_social, ID_Pack, banco_id, Estado_de_Pago, id_vendedor, comuna_id, Monto, fecha_compra,direccion, hora_de_compra, Nombre_receptor, Contacto_receptor, status_venta, Codigo_de_Transaccion, Estado_de_Orden, id_boleta) VALUES(?,?,?,?,?,?)";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setString(1, vent.getRut());
          ps.setString(2, vent.getNombre());
          ps.setBoolean(3, vent.isEstado());
//          ps.setDate(4, (java.sql.Date) client.getNacimiento());
          ps.setString(4, vent.getCelular());
          ps.setString(5, vent.getDireccion());
          ps.setString(6, vent.getRedSocial());
          ps.execute();
          System.out.println("registrado");
          return true;
      } catch (SQLException e){
          System.out.println(e);
          return false;
      }
    }

    public boolean modificar(Venta vent){
    
      PreparedStatement ps = null;
      String sql = "UPDATE Venta SET Nombre=?, Estado=?, Nacimiento=?, Celular=?, Direccion=?, RedSocial=? WHERE RUT=?";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setString(2, vent.getNombre());
          ps.setBoolean(3, vent.isEstado());
          ps.setDate(4, (java.sql.Date) vent.getNacimiento());
          ps.setString(5, vent.getCelular());
          ps.setString(5, vent.getDireccion());
          ps.setString(5, vent.getRedSocial());
          ps.execute();
          return true;
      } catch (SQLException e){
          return false;
      }
    }
    public boolean buscar(Venta vent){
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "SELECT * FROM Ventas WHERE RUT=?  ";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setString(1, vent.getRut());
          rs = ps.executeQuery();
          if (!rs.absolute(1)) {
              System.out.println("Cliente no encontrado");
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
      String sql = "SELECT * FROM Ventas";
      try {
          ps =  conn.prepareStatement(sql);
          rs = ps.executeQuery();
          return rs;
      } catch (SQLException e){
          return null;
      }
    }
    
    
}
