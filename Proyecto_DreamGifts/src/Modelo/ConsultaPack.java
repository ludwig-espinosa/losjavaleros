package Modelo;

import static Modelo.Conexion.conn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultaPack {
    
    public boolean registrar(Pack pack){
    
      PreparedStatement ps = null;
      String sql = "INSERT INTO Pack (nombre, precio, estado) VALUES(?,?,?)";
      try {
          ps =  conn.prepareStatement(sql);;
          ps.setInt(2, pack.getPrecio());
          ps.setString(1, pack.getNombre());
          ps.setInt(2, pack.getPrecio());
          ps.setBoolean(3, pack.isEstado());
          ps.execute();
          System.out.println("registrado");
          return true;
      } catch (SQLException e){
          System.out.println(e);
          return false;
      }
    }

    public boolean modificar(Pack pack){
    
      PreparedStatement ps = null;
      String sql = "UPDATE Pack SET nombre=?, estado=? WHERE id=?";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setString(1, pack.getNombre());
          ps.setBoolean(2, pack.isEstado());
          ps.setInt(3, pack.getId());
          ps.execute();
          return true;
      } catch (SQLException e){
          return false;
      }
    }
    
    
    public boolean buscar(Pack pack){
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "SELECT * FROM Pack WHERE nombre=?";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setString(1, pack.getNombre());
          rs = ps.executeQuery();
          if (!rs.absolute(1)) {
              System.out.println("Pack no encontrado");
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
      String sql = "SELECT * FROM pack";
      try {
          ps =  conn.prepareStatement(sql);
          rs = ps.executeQuery();
          return rs;
      } catch (SQLException e){
          return null;
      }
     }

    public boolean agregarArticulo(DetallePack dp, Pack pack){
    
      PreparedStatement ps = null;
      String sql = "INSERT INTO detalle_pack (ID_Pack, ID_Articulo, cantidad) VALUES(?,?,?)";
      dp.setIdPack(pack.getId());
      try {
          ps =  conn.prepareStatement(sql);
          ps.setInt(1, dp.getIdPack());
          ps.setInt(2, dp.getIdArticulo());
          ps.setInt(3, dp.getCantidad());
          ps.execute();
          System.out.println("registrado");
          return true;
      } catch (SQLException e){
          System.out.println(e);
          return false;
      }
    }
     
     
     
}
