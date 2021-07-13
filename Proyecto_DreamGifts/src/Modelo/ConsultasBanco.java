
package Modelo;

import DreamsGifts.Administracion;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConsultasBanco extends Conexion{
    
    public boolean registrar(Banco ban){
    
      PreparedStatement ps = null;
      String sql = "INSERT INTO bancos (codigo, nombre) VALUES(?,?)";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setString(1, ban.getCodigo());
          ps.setString(2, ban.getNombre());
          Administracion admin = new Administracion();
          ps.execute();
          return true;
      } catch (SQLException e){
          return false;
      }
    }

    public boolean modificar(Banco ban){
    
      PreparedStatement ps = null;
      String sql = "UPDATE bancos SET codigo=?, nombre=? WHERE banco_id=?";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setString(1, ban.getCodigo());
          ps.setString(2, ban.getNombre());
          ps.setInt(3, ban.getId());
          Administracion admin = new Administracion();
          ps.execute();
          return true;
      } catch (SQLException e){
          return false;
      }
    }
}
