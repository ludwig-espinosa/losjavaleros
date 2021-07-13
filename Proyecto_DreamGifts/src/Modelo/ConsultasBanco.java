
package Modelo;

import DreamsGifts.Administracion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultasBanco extends Conexion{
    
    public boolean registrar(Banco ban){
    
      PreparedStatement ps = null;
      String sql = "INSERT INTO bancos (codigo, nombre, estado) VALUES(?,?,?)";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setString(1, ban.getCodigo());
          ps.setString(2, ban.getNombre());
          ps.setBoolean(3, ban.getEstado());
          ps.execute();
          return true;
      } catch (SQLException e){
          return false;
      }
    }

    public boolean modificar(Banco ban){
    
      PreparedStatement ps = null;
      String sql = "UPDATE bancos SET codigo=?, nombre=?, estado=? WHERE banco_id=?";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setString(1, ban.getCodigo());
          ps.setString(2, ban.getNombre());
          ps.setBoolean(3, ban.getEstado());
          ps.setInt(4, ban.getId());
          ps.execute();
          return true;
      } catch (SQLException e){
          return false;
      }
    }
/* en espera para ver si lo enlazo directamente co la tabla*/
    public boolean buscar(Banco ban){
    
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "SELECT * FROM bancos WHERE banco_id=?";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setInt(1, ban.getId());
          rs = ps.executeQuery();
          
          
          return true;
      } catch (SQLException e){
          return false;
      }
    }
    





}
