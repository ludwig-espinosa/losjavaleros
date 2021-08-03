
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

    public ResultSet buscarTodos(Date inicio, Date fin, String rut){
        PreparedStatement ps = null;
        java.sql.Date inicioS, finS;
        inicioS = new java.sql.Date(inicio.getTime());
        finS = new java.sql.Date(fin.getTime());
        ResultSet rs = null;
        String sql = "SELECT * FROM Ventas WHERE (fecha_compra BETWEEN ? AND ?)";

        try {
            ps =  conn.prepareStatement(sql);
            ps.setDate(1,inicioS);
            ps.setDate(2,finS);
            System.out.println(ps);
            rs = ps.executeQuery();
            System.out.println("query ejecutada");
            if (!rs.absolute(1)) {
                System.out.println("clientes no escontrados no encontrado");
              return rs; 
            }
            return rs;
        } catch (SQLException e){
            System.out.println(e);
            return rs;
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
