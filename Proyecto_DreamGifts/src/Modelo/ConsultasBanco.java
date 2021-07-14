
package Modelo;

import DreamsGifts.Administracion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultasBanco extends Conexion{
    
    public boolean registrar(Banco ban){
    
      PreparedStatement ps = null;
      String sql = "INSERT INTO Bancos (cod_banco, nombre, estado) VALUES(?,?,?)";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setString(1, ban.getCodigo());
          ps.setString(2, ban.getNombre());
          ps.setBoolean(3, ban.getEstado());
          ps.execute();
          System.out.println("registrado");
          return true;
      } catch (SQLException e){
          System.out.println(e);
          return false;
      }
    }

    public boolean modificar(Banco ban){
    
      PreparedStatement ps = null;
      String sql = "UPDATE Bancos SET cod_banco=?, nombre=?, estado=? WHERE banco_id=?";
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
      String sql = "SELECT * FROM Bancos WHERE cod_banco=?";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setInt(1, ban.getId());
          rs = ps.executeQuery();
          if (!rs.isAfterLast()) {
              System.out.println("banco no encontrado");
            return false; 
          } 
          
          return true;
      } catch (SQLException e){
          return false;
      }
    }
    





}
