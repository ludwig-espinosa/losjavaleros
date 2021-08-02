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
     
    public String buscarNamePorId (String pack){
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ID_Pack, nombre, precio FROM Pack ID_Pack=?  ";
        try {
            ps = conn.prepareStatement (sql);
            ps.setString(1, pack);
            rs = ps.executeQuery();
            rs.next();
            if (!rs.absolute(1)) {
                System.out.println("Cliente no encontrado");
              return "Cliente No Encontrado"; 
            }else{
                System.out.println("cliente encontrado");
                pack = rs.getString(2);
                return pack;
            }
            
        } catch (SQLException e) {
           System.out.println(e);
            return null;
        }
    }
    public int buscarPrecioPorNombre (String nombre){
        PreparedStatement ps = null;
        ResultSet rs = null;
        int precio;
        String sql = "SELECT * FROM Pack WHERE nombre=?  ";
        try {
            ps = conn.prepareStatement (sql);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            rs.next();
            if (!rs.absolute(1)) {
                System.out.println("Cliente no encontrado");
              return -1; 
            }else{
                System.out.println("cliente encontrado");
                precio = rs.getInt(3);
                return precio;
            }
            
        } catch (SQLException e) {
           System.out.println(e);
            return -1;
        }
    }
     public int PackIdPorNombre (String Pack){
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id;
        String sql = "SELECT * FROM Pack WHERE nombre=?  ";
        try {
            ps =  conn.prepareStatement(sql);
            ps.setString(1, Pack);
            rs = ps.executeQuery();
            rs.next();
            if (!rs.absolute(1)) {
                System.out.println("Cliente no encontrado");
              return -1; 
            }else{
                id = Integer.parseInt(rs.getString(1));
                return id;
            }
                
        } catch (SQLException e){
            return -1;
        }
     }
     
     
}