
package Modelo;

import java.sql.PreparedStatement;

public class ConsultasBanco extends Conexion{
    
    public registrar(Banco ban){
    
      PreparedStatement ps = null;
      String sql = "INSERT INTO bancos (codigo, nombre) VALUES(?,?)";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setString(1, ban.getCodigo());
          ps.setString(2, ban.getNombre());
          return true;
      } catch {
          return false;
      }
    
    }
}
