
package Modelo;

import DreamsGifts.Administracion;
import static Modelo.Conexion.conn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConsultaReportes{
//    Establecemos formato para transformar las fechas
    DateFormat form = new SimpleDateFormat("dd-MM-yyyy");

    public boolean buscar(Date inicio, Date fin, String rut){
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "SELECT * FROM Ventas WHERE (fecha_compra BETWEEN ? AND ?) AND (RUT = ?)";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setDate(1, (java.sql.Date) inicio);
          ps.setString(2, fin);
          ps.setString(3, rut);
          rs = ps.executeQuery();
          if (!rs.absolute(1)) {
              System.out.println("banco no encontrado");
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
      String sql = "SELECT * FROM Bancos";
      try {
          ps =  conn.prepareStatement(sql);
          rs = ps.executeQuery();
          return rs;
      } catch (SQLException e){
          return null;
      }
    }





}
