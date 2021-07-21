
package Controlador;

import DreamsGifts.Login;
import DreamsGifts.Principal;
import Modelo.Conexion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CtrPrincipal implements ActionListener{
    private Principal princ = new Principal();
//    public Conexion conn = new Conexion();
    private CtrAdministracion ctAd = new CtrAdministracion();
    static Login lgn;
    public CtrPrincipal(Login login){
        this.princ.btnAdmin.addActionListener(this);
        this.princ.BotonSalir.addActionListener(this);
        lgn = login;
        
    }

       public void iniciar(){
       if (!princ.isVisible()){
           princ.setVisible(true);
       }
   }  
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == princ.btnAdmin){
            this.ctAd.iniciar();
        }
        
        if (e.getSource() == princ.BotonSalir){
            princ.setVisible(false);
            lgn.setVisible(true);
            
        }
    }
}
