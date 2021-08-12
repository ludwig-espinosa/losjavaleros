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
/**
 *
 * @author lespinosa
 */
public class ConsultaOrdenCompra {
       public boolean registrar(OrdenCompra OrdComp){
      PreparedStatement ps = null;
      String sql = "INSERT INTO Orden_Compra (id_proveedor, fecha_orden, idDetalleOrdenCompra) VALUES(?,?,?)";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setInt(1,OrdComp.getId_proveedor());
          ps.setDate(2, (Date) OrdComp.getFecha_orden());
          ps.setInt(3, OrdComp.getIdDetalle_Orden_Compra());
          ps.execute();
          System.out.println("registrado");
          return true;
      } catch (SQLException e){
          System.out.println(e);
          return false;
      }
    }
    public boolean modificar(OrdenCompra OrdComp){
      PreparedStatement ps = null;
      String sql = "UPDATE Orden_Compra SET id_proveedor=?, fecha_orden=?, idDetalleOrdenCompra=? WHERE idOrden_Compra=?";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setInt(1, OrdComp.getId_proveedor());
          ps.setDate(2, (Date) OrdComp.getFecha_orden());
          ps.setInt(3, OrdComp.getIdDetalle_Orden_Compra());
          ps.execute();
          return true;
      } catch (SQLException e){
          return false;
      }
    }
    public boolean buscar(OrdenCompra OrdComp){
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "SELECT * FROM Orden_Compra WHERE idOrden_Compra=?  ";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setInt(1,OrdComp.getIdOrden_Compra());
          rs = ps.executeQuery();
          if (!rs.absolute(1)) {
              System.out.println("facturas no encontrado");
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
      String sql = "SELECT * FROM Orden_Compra";
      try {
          ps =  conn.prepareStatement(sql);
          rs = ps.executeQuery();
          return rs;
      } catch (SQLException e){
          return null;
      }
    }

}
