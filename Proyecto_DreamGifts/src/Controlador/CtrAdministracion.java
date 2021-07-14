package Controlador;

import DreamsGifts.Administracion;
import Modelo.Banco;
import Modelo.ConsultasBanco;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CtrAdministracion implements ActionListener {
   private static Administracion admin = new Administracion();
   private static ConsultasBanco con = new ConsultasBanco();

  
   public CtrAdministracion(){
       admin.bancosButtonCancel.addActionListener(this);
       admin.bancosButtonSave.addActionListener(this);
       
   }
   
   
   
   
   public void iniciar(){
       if (!admin.isVisible()){
           admin.setVisible(true);
       }
   }

   public boolean agregarBancos(){
       Banco ban = new Banco();
       ban.setCodigo(admin.bancosTextcode.getText());
       ban.setEstado(admin.bancosEstadoActive.isSelected());
       ban.setNombre(admin.bancosTextName.getText());
         if (!con.buscar(ban)) {
             System.out.println("intentando agregar");
            con.registrar(ban);
            return true;
        } else{
             return con.modificar(ban);
         }
        
   }
   
   
   
   
   
    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getSource() == admin.bancosButtonSave){
        this.agregarBancos();   
       }
    }
   
   
}
