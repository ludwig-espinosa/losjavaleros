package Controlador;

import DreamsGifts.Administracion;
import Modelo.Banco;
import Modelo.ConsultasBanco;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class CtrAdministracion implements ActionListener {
   private static Administracion admin = new Administracion();
   private static ConsultasBanco con = new ConsultasBanco();

   public void iniciar(){
       if (!admin.isVisible()){
           admin.setVisible(true);
       }
   }  
   
   public CtrAdministracion(){
       this.iniciarBanco();
       this.actualizarTablaBancos();
   }
   
   
   
 /*Implementacion CRUD Banco */

   public void iniciarBanco(){
       admin.bancosButtonCancel.addActionListener(this);
       admin.bancosButtonSave.addActionListener(this);
            
   }
   
   public boolean agregarBancos(){
       Banco ban = new Banco();
       ban.setCodigo(admin.bancosTextcode.getText());
       ban.setEstado(admin.bancosEstadoActive.isSelected());
       ban.setNombre(admin.bancosTextName.getText());
       admin.bancosTextName.setText("");
       admin.bancosTextcode.setText("");
         if (!con.buscar(ban)) {
             System.out.println("intentando agregar");
            con.registrar(ban);
            return true;
        } else{
             System.out.println("a modificar");
             return con.modificar(ban);
         }
        
   }
   
   
   public void borrarTablaBancos(){
       DefaultTableModel rm = (DefaultTableModel) admin.bancosTable.getModel();
       while (rm.getRowCount() > 0){
           rm.removeRow(0);
       }
   }
   

    public void actualizarTablaBancos(){
        this.borrarTablaBancos();
        ResultSet rs = con.llamarTodos();
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
   
   
   
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == admin.bancosButtonSave){
            this.agregarBancos();
            this.actualizarTablaBancos();
        }

    }
    
}