
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
    ConsultaCliente conCli = new ConsultaCliente();

    public ResultSet buscar(Date inicio, Date fin, String rut){
      PreparedStatement ps = null;
      ResultSet rs = null;
      int idCliente = conCli.buscarIdPorRut(rut)
        if (rut != null) {
            String sql = "SELECT * FROM Ventas WHERE (fecha_compra BETWEEN ? AND ?) AND (ID_Cliente = ?)";
            try {
                ps =  conn.prepareStatement(sql);
                ps.setDate(1, (java.sql.Date) inicio);
                ps.setDate(2, (java.sql.Date) fin);
                ps.setInt(3, idCliente);
                rs = ps.executeQuery();
                if (!rs.absolute(1)) {
                    System.out.println("clientes no escontrados no encontrado");
                  return rs; 
                }
                return rs;
            } catch (SQLException e){
                return null;
            }   
        } else {
            return null;
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
