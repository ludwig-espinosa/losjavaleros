package Controlador;

import DreamsGifts.Administracion;
import Modelo.Banco;
import Modelo.Comuna;
import Modelo.ConsultaRedSocial;
import Modelo.ConsultaUsuarios;
import Modelo.ConsultasBanco;
import Modelo.RedSocial;
import Modelo.Usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CtrAdministracion implements ActionListener {
   private static Administracion admin = new Administracion();
   private static ConsultasBanco conBanco = new ConsultasBanco();
   private static ConsultaRedSocial conRrss = new ConsultaRedSocial();
   private static ConsultaUsuarios conUser = new ConsultaUsuarios();
   private static ConsultaComuna concomuna = new ConsultaComuna();

   public void iniciar(){
       if (!admin.isVisible()){
           admin.setVisible(true);
       }
   }  
   
   public CtrAdministracion(){
       this.iniciarBanco();
       this.iniciarRedSocial();
       this.iniciarUsuarios();
   }
   
   
   public void borrarTabla(JTable tabla){
       DefaultTableModel rm = (DefaultTableModel) tabla.getModel();
       while (rm.getRowCount() > 0){
           rm.removeRow(0);
       }
   }
      
   
   
   
   
   
   
   
 /*Implementacion CRUD Banco */

   public void iniciarBanco(){
       admin.bancosButtonCancel.addActionListener(this);
       admin.bancosButtonSave.addActionListener(this);
       this.actualizarTablaBancos();
            
   }
   
   public boolean agregarBancos(){
       Banco ban = new Banco();
       ban.setCodigo(admin.bancosTextcode.getText());
       ban.setEstado(admin.bancosEstadoActive.isSelected());
       ban.setNombre(admin.bancosTextName.getText());
       admin.bancosTextName.setText("");
       admin.bancosTextcode.setText("");
         if (!conBanco.buscar(ban)) {
             System.out.println("intentando agregar");
            conBanco.registrar(ban);
            return true;
        } else{
             System.out.println("a modificar");
             return conBanco.modificar(ban);
         }
        
   }
   


    public void actualizarTablaBancos(){
        this.borrarTabla(admin.bancosTable);
        ResultSet rs = conBanco.llamarTodos();
        Object[] row;
        row = new Object[3];
        DefaultTableModel rm = (DefaultTableModel) admin.bancosTable.getModel();
        try {
            while (rs.next()){
                row[0] = rs.getString("cod_banco");
                row[1] = rs.getString("Nombre");
                row[2] = rs.getBoolean("estado");
                rm.addRow(row);  
            }
            } catch (SQLException ex) {
        }
    }
   
    /*       Fin CRUD Banco        */
    
    
    
    
    /*       inicio CRUD Red Social        */

    
   public void iniciarRedSocial(){
       admin.RrssBtnSave.addActionListener(this);
       admin.RrssBtnCancel.addActionListener(this);
       this.actualizarTablaRrss();
   }
   
   public boolean agregarRrss(){
       RedSocial rrss = new RedSocial();
       rrss.setCodigo(admin.RrssTextCode.getText());
       rrss.setEstado(admin.RrssEstadoAct.isSelected());
       rrss.setNombre(admin.RrssTextName.getText());
       admin.RrssTextCode.setText("");
       admin.RrssTextName.setText("");
         if (!conRrss.buscar(rrss)) {
             System.out.println("intentando agregar");
            conRrss.registrar(rrss);
            return true;
        } else{
             System.out.println("a modificar");
             return conRrss.modificar(rrss);
         }
        
   }
   

    public void actualizarTablaRrss(){
        this.borrarTabla(admin.RrssTable);
        ResultSet rs = conRrss.llamarTodos();
        Object[] row;
        row = new Object[3];
        DefaultTableModel rm = (DefaultTableModel) admin.RrssTable.getModel();
        try {
            while (rs.next()){
                row[0] = rs.getString("cod_rrss");
                row[1] = rs.getString("Nombre");
                row[2] = rs.getBoolean("estado");
                rm.addRow(row);  
            }
            } catch (SQLException ex) {
        }
    }
   
   
    /*       Fin CRUD Redes Sociales        */
    
    
    
    /*       inicio CRUD Usuarios        */

   public void iniciarUsuarios(){
       admin.usuariosBtnAdd.addActionListener(this);
       admin.usuariosBtnCancel.addActionListener(this);
       this.actualizarTablaUsuarios();
   }
   
   public boolean agregarUsuario(){
       Usuario user = new Usuario();
       user.setUser(admin.usuariosTextUser.getText());
       user.setEstado(admin.usuariosEstadoAct.isSelected());
       user.setNombre(admin.usuariosTextName.getText());
       user.setContraseña(admin.usuariosTextPassw.getText());
       user.setRut(admin.usuariosTextRut.getText());
       admin.usuariosTextName.setText("");
       admin.usuariosTextUser.setText("");
       admin.usuariosTextPassw.setText("");
       admin.usuariosTextRut.setText("");

         if (!conUser.buscar(user)) {
             System.out.println("intentando agregar");
             conUser.registrar(user);
            return true;
        } else{
             System.out.println("a modificar");
             return conUser.modificar(user);
         }
        
   }
   
   public void actualizarTablaUsuarios(){
        this.borrarTabla(admin.usuariosTable);
        ResultSet rs = conUser.llamarTodos();
        Object[] row;
        row = new Object[5];
        DefaultTableModel rm = (DefaultTableModel) admin.usuariosTable.getModel();
        try {
            while (rs.next()){
                row[0] = rs.getString("RUT");
                row[1] = rs.getString("Usuario");
                row[2] = rs.getString("Nombre");
                row[3] = rs.getString("Contraseña");
                row[4] = rs.getBoolean("estado");

                rm.addRow(row);  
            }
            } catch (SQLException ex) {
                System.out.println(ex);
        }
    }
   

   
    /*Implementacion CRUD Comuna */

   public void iniciarcomuna(){
       admin.cancelcomuna.addActionListener(this);
       admin.savecomuna.addActionListener(this);
       this.actualizarTablaBancos();
            
   }
   
   public boolean agregarComuna(){
       Comuna com = new Comuna();
       com.setCodigo(admin.codetxtcomuna.getText());
       com.setEstado(admin.comunaestadoactiv.isSelected());
       com.setNombre(admin.namecomuna.getText());
       admin.namecomuna.setText("");
       admin.codetxtcomuna.setText("");
         if (!concomuna.buscar(com)) {
             System.out.println("intentando agregar");
            concomuna.registrar(com);
            return true;
        } else{
             System.out.println("a modificar");
             return concomuna.modificar(com);
         }
        
   }
   


    public void actualizarTablaComuna(){
        this.borrarTabla(admin.comunatable);
        ResultSet rs = concomuna.llamarTodos();
        Object[] row;
        row = new Object[3];
        DefaultTableModel rm = (DefaultTableModel) admin.comunatable.getModel();
        try {
            while (rs.next()){
                row[0] = rs.getString("cod_comuna");
                row[1] = rs.getString("Nombre");
                row[2] = rs.getBoolean("estado");
                rm.addRow(row);  
            }
            } catch (SQLException ex) {
        }
    }
   
    /*       Fin CRUD Comuna        */
   
   
   
   
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == admin.bancosButtonSave){
            this.agregarBancos();
            this.actualizarTablaBancos();
        }
        if (e.getSource() == admin.RrssBtnSave) {
            this.agregarRrss();
            this.actualizarTablaRrss();
        }
        if (e.getSource() == admin.usuariosBtnAdd) {
            this.agregarUsuario();
            this.actualizarTablaUsuarios();
        }
        if (e.getSource() == admin.savecomuna) {
            this.agregarComuna();
            this.actualizarTablaComuna();
        }
    }
    
}