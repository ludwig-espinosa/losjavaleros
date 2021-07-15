
package Controlador;

import DreamsGifts.Principal;
import Modelo.Conexion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CtrPrincipal implements ActionListener{
    private Principal princ = new Principal();
    public Conexion conn = new Conexion();
    private CtrAdministracion ctAd = new CtrAdministracion();
    
    public CtrPrincipal(){
        this.princ.setVisible(true);
        this.princ.btnAdmin.addActionListener(this);
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == princ.btnAdmin){
            this.ctAd.iniciar();
        }
    }
}
