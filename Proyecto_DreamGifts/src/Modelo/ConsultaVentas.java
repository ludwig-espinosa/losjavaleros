/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import static Modelo.Conexion.conn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 *
 * @author lespinosa
 */
public class ConsultaVentas {
    DateFormat form = new SimpleDateFormat("dd-MM-yyyy");
    ConsultaCliente conCli = new ConsultaCliente();
    
  public boolean registrar(Venta vent){
      PreparedStatement ps = null;
      java.sql.Date fechaentrega, fechaactual;
      fechaactual = new java.sql.Date(vent.getFechaCompra().getTime());
      fechaentrega = new java.sql.Date(vent.getFechaEntrega().getTime());
      String sql = "INSERT INTO Ventas (ID_Cliente, red_social, ID_Pack, banco_id, Estado_de_Pago, comuna_id, Monto, fecha_compra,direccion, Nombre_receptor, Contacto_receptor, Codigo_de_Transaccion, Estado_de_Orden, fecha_entrega, Bloque_horario) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setInt(1, vent.getIdCliente());
          ps.setInt(2, vent.getRrss());
          ps.setInt(3, vent.getIdPack());
          ps.setInt(4, vent.getIdBanco());
          ps.setString(5, vent.getEstadoPago());
          ps.setInt(6, vent.getIdcomuna());
          ps.setInt(7, vent.getMonto());
          ps.setDate(8, fechaactual);
          ps.setString(9, vent.getDireccion());
          ps.setString(10, vent.getReceptor());
          ps.setString(11, vent.getContactoReceptor());
          ps.setString(12, vent.getCodigoTransaccion());
          ps.setString(13, vent.getEstadoDeOrden());
          ps.setDate(14, fechaentrega);
          ps.setString(15, vent.getBloqueHorario());
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
      String sql = "UPDATE Venta SET Estado_de_Orden, fecha_entrega, Bloque_horario, direccion, Nombre_receptor, Contacto_receptor, comuna_id) VALUES(?,?,?,?,?,?,?)";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setString(1, vent.getEstadoDeOrden());
          ps.setDate(2, (java.sql.Date) vent.getFechaEntrega());
          ps.setString(3, vent.getBloqueHorario());
          ps.setString(4, vent.getDireccion());
          ps.setString(5, vent.getReceptor());
          ps.setString(6, vent.getContactoReceptor());      
          ps.setInt(7, vent.getIdcomuna()); 
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
          ps.setInt(1, vent.getIdCliente());
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
          return rs;
      }
    }
    
    
}
