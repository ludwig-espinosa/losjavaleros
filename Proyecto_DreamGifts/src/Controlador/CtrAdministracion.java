package Controlador;

import DreamsGifts.Administracion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CtrAdministracion implements ActionListener {
   private static Administracion admin = new Administracion();

  
   
   public void iniciar(){
       if (!admin.isVisible()){
           admin.setVisible(true);
       }
   }

    @Override
    public void actionPerformed(ActionEvent e) {
       
    }
   
   
}
